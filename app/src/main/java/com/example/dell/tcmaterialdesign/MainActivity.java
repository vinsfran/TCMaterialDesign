package com.example.dell.tcmaterialdesign;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dell.tcmaterialdesign.domain.TiposReclamos;
import com.example.dell.tcmaterialdesign.fragments.TiposReclamosFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "LOG";
    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Main Activity");
        mToolbar.setSubtitle("Sub titulo");
        mToolbar.setLogo(R.drawable.ic_logo);
        setSupportActionBar(mToolbar);

        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;
                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("http://www.facebook.com"));
                        break;
                }
                startActivity(it);
                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);
        mToolbarBottom.findViewById(R.id.iv_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "iv_settings apretado", Toast.LENGTH_SHORT).show();
            }
        });

        //FRAGMENT
        TiposReclamosFragment frag = (TiposReclamosFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null){
            frag = new TiposReclamosFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<TiposReclamos> getSetTiposReclamosList(int qtd) {
        int[] codigos = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] nombres = new String[]{"Banca 1", "Banca 2", "Banca 3", "Banca 4", "Banca 5", "Banca 6", "Banca 7", "Banca 8", "Banca 9", "Banca 10"};
        List<TiposReclamos> tiposReclamos = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            TiposReclamos tipoReclamo = new TiposReclamos(codigos[i % codigos.length], nombres[i % nombres.length]);
            tiposReclamos.add(tipoReclamo);
        }
        return (tiposReclamos);
    }
}
