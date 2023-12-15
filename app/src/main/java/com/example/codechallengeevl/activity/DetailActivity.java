package com.example.codechallengeevl.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.codechallengeevl.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImage;
    TextView tagLine, detailFirstBrewed, detailDesc, detailAbv, detailIbu, detailTargetfg, detailTargetog,
            detailEbc, detailSrm, detailPh, detailAttenuationlevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        configView();
    }

    private void configView(){
        // instancia que recupera la data proveniente de la actividad BeersActivity.java
        Bundle bundle = getIntent().getExtras();

        // se setea el título del actionbar
        getSupportActionBar().setTitle(DetailActivity.this.getResources().getString(R.string.tituloDetailCervezas));
        // se setea el subtítulo del actionbar
        getSupportActionBar().setSubtitle(bundle.getString(DetailActivity.this.getResources().getString(R.string.keyName)));
        // se agrega el botón de flecha en el actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // instancias de los objetos de la vista
        detailImage = findViewById(R.id.imageViewDetail);
        tagLine = findViewById(R.id.tagLine);
        detailFirstBrewed = findViewById(R.id.firstBrewed);
        detailDesc = findViewById(R.id.desc);
        detailAbv = findViewById(R.id.abv);
        detailIbu = findViewById(R.id.ibu);
        detailTargetfg = findViewById(R.id.targetfg);
        detailTargetog = findViewById(R.id.targetog);
        detailEbc = findViewById(R.id.ebc);
        detailSrm = findViewById(R.id.srm);
        detailPh = findViewById(R.id.ph);
        detailAttenuationlevel = findViewById(R.id.attenuationlevel);

        if(bundle != null){
            // se setea la data recuperada de la actividad BeersActivity.java
            Picasso.get().load(bundle.getString(DetailActivity.this.getResources().getString(R.string.keyImage))).into(detailImage);
            tagLine.setText(bundle.getString(DetailActivity.this.getResources().getString(R.string.keyTagLine)));
            detailDesc.setText(bundle.getString(DetailActivity.this.getResources().getString(R.string.keyDescription)));
            detailFirstBrewed.setText(bundle.getString(DetailActivity.this.getResources().getString(R.string.keyFirstBrewed)));
            detailAbv.setText(bundle.getString("Abv"));
            detailIbu.setText(bundle.getString("Ibu"));
            detailTargetfg.setText(bundle.getString("Targetfg"));
            detailTargetog.setText(bundle.getString("Targetog"));
            detailEbc.setText(bundle.getString("Ebc"));
            detailSrm.setText(bundle.getString("Srm"));
            detailPh.setText(bundle.getString("Ph"));
            detailAttenuationlevel.setText(bundle.getString("Attenuationlevel"));
        }
    }

    // se llama esta función cuando se hace clic en el botón de flecha del actionbar para regresar a la activity anterior en este caso BeersActivity
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(DetailActivity.this, BeersActivity.class);
        startActivity(intent);
        return true;
    }
}