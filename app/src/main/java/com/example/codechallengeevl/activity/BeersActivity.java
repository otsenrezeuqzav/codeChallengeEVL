package com.example.codechallengeevl.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.codechallengeevl.R;
import com.example.codechallengeevl.Utility.Utilities;
import com.example.codechallengeevl.adapter.MyAdapter;
import com.example.codechallengeevl.db.AppDatabase;
import com.example.codechallengeevl.db.Beer;
import com.example.codechallengeevl.model.BeersModel;
import com.example.codechallengeevl.network.APIClient;
import com.example.codechallengeevl.network.APIService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeersActivity extends AppCompatActivity {

    private static final String nameDbCadena = Utilities.nameDB();
    private List<BeersModel> lstBeers;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    // variables para manejar el número de página y para limitar las mismas
    int page = 1, limit = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);

        // instancia de lista de cervezas
        lstBeers = new ArrayList<BeersModel>();

        loadingPB = findViewById(R.id.idPBLoading);
        nestedSV = findViewById(R.id.idNestedSV);

        // se crear un callback para manejar la pulsación del botón Atrás
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // se personaliza el comportamiento cuando se presiona el botón Atrás
                new AlertDialog.Builder(BeersActivity.this)
                        .setTitle(BeersActivity.this.getResources().getString(R.string.alertDlgTitulo))
                        .setCancelable(false)
                        .setNegativeButton(R.string.cancelar, null)
                        .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(BeersActivity.this.getResources().getString(R.string.exit), true);
                                startActivity(intent);
                            }
                        }).show();
            }
        };
        // se agrega el callback a OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        // método de scroll change listener para el nested scroll view
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Al cambiar el desplazamiento, se comprueba cuando los usuarios se desplazan hacia abajo.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // se incrementa el número de página, se hace visible la progress bar y se llama al método configView
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    configView(page, limit);
                }
            }
        });

        configView(page, limit);
    }

    private void configView(int page, int limit){
        // se setea el título del actionbar
        getSupportActionBar().setTitle(BeersActivity.this.getResources().getString(R.string.tituloLstCervezas));

        // se agrega el botón de flecha en el actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Instancia y creación de base de datos
        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                nameDbCadena
        ).allowMainThreadQueries().build(); // permite realizar consultas a la base de datos desde el hilo principal

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // se comprueba si el número de página es mayor que el límite
        if (page > limit) {
            // se muestra mensaje cuando page > limit
            Toast.makeText(this, "Esos son todos los datos contemplados..", Toast.LENGTH_SHORT).show();

            // se oculta barra de progreso
            loadingPB.setVisibility(View.GONE);

            // se llama useDB para usar la información de la base de datos SQLite
            useDB(appDatabase, recyclerView);
            return;
        }

            Toast.makeText(BeersActivity.this, BeersActivity.this.getResources().getString(R.string.infoUseWsMsg), Toast.LENGTH_LONG).show();
            // Thread, permite llamar un método en un nuevo hilo, esperar a que este termine y recoger los resultados
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // se instancia la clase APIService y se hace el llamado al API
                        APIService apiService = APIClient.getInstance().createService(APIService.class);
                        Call<List<BeersModel>> call = apiService.ConsultaCervezasPOST(page);
                        call.enqueue(new Callback<List<BeersModel>>() { //enqueue hace que la consulta sea asíncrona

                            // onResponse sirve para indicar que hacer cuando la consulta es correcta y se tienen datos
                            @Override
                            public void onResponse(Call<List<BeersModel>> call, Response<List<BeersModel>> response) {

                                // instancia de la Entity Beer
                                Beer beer = new Beer();

                                // se agrega a la lista de cervezas
                                lstBeers.addAll(response.body());

                                // se valida lista vacia
                                if (lstBeers.size() > 0) {
                                    // se itera en la lista de cervezas
                                    Iterator<BeersModel> iteratorMe = lstBeers.iterator();
                                    while (iteratorMe.hasNext()) {
                                        BeersModel beersModel = iteratorMe.next();

                                        // se setea la lista de cervezas en el recyclerView
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), lstBeers));


                                        // se inserta en la base de datos SQLite: dbBeers si el registro no existe
                                        // seteo de la data
                                        if (appDatabase.daoBeer().getCount(beersModel.getId()) == 0) {
                                            beer.setId(beersModel.getId());
                                            beer.setName(beersModel.getName());
                                            beer.setTagline(beersModel.getTagline());
                                            beer.setFirst_brewed(beersModel.getFirst_brewed());
                                            beer.setDescription(beersModel.getDescription());
                                            beer.setImage_url(beersModel.getImage_url());
                                            beer.setAbv(beersModel.getAbv());
                                            beer.setIbu(beersModel.getIbu());
                                            beer.setTarget_fg(beersModel.getTarget_fg());
                                            beer.setTarget_og(beersModel.getTarget_og());
                                            beer.setEbc(beersModel.getEbc());
                                            beer.setSrm(beersModel.getSrm());
                                            beer.setPh(beersModel.getPh());
                                            beer.setAttenuation_level(beersModel.getAttenuation_level());
                                            appDatabase.daoBeer().insertarBeer(beer);
                                        }
                                    }
                                }
                            }

                            // OnFailure sirve para indicar que se hace si hay algún error de tipo Throwable
                            @Override
                            public void onFailure(Call<List<BeersModel>> call, Throwable t) {
                                Toast.makeText(BeersActivity.this, BeersActivity.this.getResources().getString(R.string.onFailureMsg), Toast.LENGTH_SHORT).show();
                                // se oculta barra de progreso
                                loadingPB.setVisibility(View.GONE);

                                // se llama useDB para usar la información de la base de datos SQLite
                                useDB(appDatabase, recyclerView);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    // funcíón para usar la BD SQLite
    private void useDB(AppDatabase appDatabase, RecyclerView recyclerView){
        Toast.makeText(BeersActivity.this, BeersActivity.this.getResources().getString(R.string.infoUseSQLiteDbMsg), Toast.LENGTH_SHORT).show();
        // traer información de la base de datos beerTD y asignar a la lista de cervezas
        lstBeers = appDatabase.daoBeer().obtenerData();

        // se itera en la lista de cervezas
        Iterator<BeersModel> iteratorMe = lstBeers.iterator();

        // se setea la información al recyclerview
        while (iteratorMe.hasNext()) {
            BeersModel beersModel = iteratorMe.next();
            Log.e("nombre", beersModel.getName());

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(new MyAdapter(getApplicationContext(),lstBeers));
        }
    }


    // se llama esta función cuando se hace clic en el botón de flecha del actionbar para regresar a la activity anterior
    // en este caso se se pregunta si se desea salir de la aplicación a través de una venta emergente alertdialog
    @Override
    public boolean onSupportNavigateUp() {
        new AlertDialog.Builder(this)
                .setTitle(BeersActivity.this.getResources().getString(R.string.alertDlgTitulo))
                .setCancelable(false)
                .setNegativeButton(BeersActivity.this.getResources().getString(R.string.cancelar), null)
                .setPositiveButton(BeersActivity.this.getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {// un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(BeersActivity.this.getResources().getString(R.string.exit), true);
                        startActivity(intent);
                    }
                }).show();
        return true;
    }

}