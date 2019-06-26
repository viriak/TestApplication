package com.test.osoft.testapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.presenter.match.GridMatchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    //private ViewPager viewPager;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            mViewPager = (ViewPager) findViewById(R.id.pagerMarket);

            setupViewPager(mViewPager);

            toolbar = (Toolbar) findViewById(R.id.toolbarMarket);
            // Preparar las pestañas
            //TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsMarket);
            tabLayout = (TabLayout) findViewById(R.id.tabsMarket);
            tabLayout.setupWithViewPager(mViewPager);
            toolbar.setTitle("December 2018");
            setupViewPager(mViewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabsMarket);
            tabLayout.setupWithViewPager(mViewPager);
            //tabLayout.getTabAt(0).setIcon(R.drawable.beer_can);
            tabLayout.getTabAt(0).setText("FIXTURES");
            //tabLayout.getTabAt(1).setIcon(R.drawable.cold_drink);
            tabLayout.getTabAt(1).setText("RESULTS");

        } catch (Exception ex) {
            String e = ex.toString();

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //Toast.makeText(MarketActivity.this, "TOTAL CARGA PRODUCTOS DATOS!!!!!!!!!!!: " + totalProductsListBebidas, Toast.LENGTH_LONG).show();
        //GridFragment.newInstance(1);
        //adapter.addFragment(GridFragment.newInstance(1), getString(R.string.title_section1));
        adapter.addFragment(GridMatchFragment.newInstance(1, getImageListXType(1)), getString(R.string.title_section1));
        adapter.addFragment(GridMatchFragment.newInstance(2, getImageListXType(2)), getString(R.string.title_section2));
        viewPager.setAdapter(adapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Toast.makeText(getBaseContext(), "Cargando Item: " + position + " Total: " + totalProductsListBebidas, Toast.LENGTH_LONG).show();

            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return GridMatchFragment.newInstance(0, getImageListXType(1));
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return GridMatchFragment.newInstance(1, getImageListXType(2));
                default:
                    return null;
            }

            //return mFragments.get(position);
        }
        //return null;


        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }


    }

    public ArrayList<Fixture> getImageListXType(int typeImage)
    {
        //CAMBIOS GAAL PARA CARGAR INFORMACIÓN
        ArrayList<Fixture> imageItemsXTypeImage = new ArrayList<>() ;
        /*if (productImageList!=null) {
            //Toast.makeText(MarketActivity.this, "TOTAL CARGA PRODUCTOS DATOS!!!!!!!!!!!: " + typeImage, Toast.LENGTH_LONG).show();
            int totalTipoProducto=0;
            for (int totalProductImageItems = 0; totalProductImageItems < productImageList.size(); totalProductImageItems++) {
                ImageItem imageItemActual = new ImageItem();
                switch(typeImage)
                {
                    case 1:
                        totalProductsListBebidas++;
                        break;
                    case 2:
                        totalProductsListLicores++;
                        break;
                    case 3:
                        totalProductsListSnacks++;
                        break;
                    case 4:
                        totalProductsListOtros++;
                        break;
                }
                //imageItemActual.setImage();
                if (productImageList.get(totalProductImageItems).getTypeProduct() == typeImage) {
                    totalTipoProducto++;
                    imageItemActual.setID(totalTipoProducto);
                    imageItemActual.setTitle(productImageList.get(totalProductImageItems).getName());
                    imageItemActual.setDescription(productImageList.get(totalProductImageItems).getDescription());
                    //GAAL VALIDAR CANTIDAD
                    imageItemActual.setQuantity(1);
                    imageItemActual.setImageURL(productImageList.get(totalProductImageItems).getUrlImage());
                    imageItemsXTypeImage.add(imageItemActual);
                }
            }
            //Toast.makeText(getBaseContext(), "¡total tipo " + typeImage + ": " + totalTipoProducto + "!", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getBaseContext(), "¡total tipo " + typeImage + ": " + totalTipoProducto + "!", Toast.LENGTH_SHORT).show();
        }*/
        return imageItemsXTypeImage;
    }


}
