package com.test.osoft.testapplication.presenter.match;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.test.osoft.testapplication.R;
import com.test.osoft.testapplication._di.DependencyProvider;
import com.test.osoft.testapplication.model.Fixture;
import com.test.osoft.testapplication.model.Result;
import com.test.osoft.testapplication.presenter.match.presentation.MatchAdapter;
import com.test.osoft.testapplication.presenter.match.presentation._adapter.GridViewFixtureAdapter;
import com.test.osoft.testapplication.presenter.match.presentation._adapter.ResultAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GridMatchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GridMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridMatchFragment extends Fragment implements MatchMVP.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ArrayList<Fixture> ItemsXFixture;
    private static final String ARG_SECTION_NUMBER = "section_number";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mMatchList;
    private MatchAdapter mMatchAdapter;
    private ResultAdapter mResultAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mEmptyView;
    private MatchPresenter mMatchPresenter;

    public GridMatchFragment() {
        // Required empty public constructor
    }

    public static GridMatchFragment newInstance(int sectionNumber, ArrayList<Fixture> mImageItemsXInventory) {
        try {
            GridMatchFragment fragment = new GridMatchFragment();
            Bundle args = new Bundle();
            ItemsXFixture = mImageItemsXInventory;
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //args.putInt(ARG_TOTAL_ITEMS, mImageItemsXInventory.size());
            fragment.setArguments(args);
            return fragment;

        } catch (Exception ex) {
            String error = ex.toString();
            return null;
            //Toast.makeText(getActivity(), "Usted NO ha aceptado los terminos y condiciones", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridMatchFragment newInstance(String param1, String param2) {
        GridMatchFragment fragment = new GridMatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private MatchAdapter.FixtureItemListener mItemListener = new MatchAdapter.FixtureItemListener() {
        @Override
        public void onFixtureClick(Fixture clickedNote) {

        }
    };

    private ResultAdapter.ResultItemListener mItemResultListener = new ResultAdapter.ResultItemListener() {
        @Override
        public void onResultClick(Result clickedNote) {

        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
            if(section_number==0) {
                mMatchAdapter = new MatchAdapter(new ArrayList<Fixture>(0),
                        mItemListener, section_number);
            }
            else
            {
                mResultAdapter = new ResultAdapter(new ArrayList<Result>(0),
                        mItemResultListener, section_number);
            }
            mMatchPresenter = new MatchPresenter(
                    DependencyProvider.provideMatchesRepository(getActivity()),
                    this);
            setRetainInstance(true);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        if(section_number==0)
            rootView = inflater.inflate(R.layout./*activity_list_view*/list_view_fixture, container, false);
        else
            rootView = inflater.inflate(R.layout./*activity_list_view*/list_view_result, container, false);
        mMatchList = (RecyclerView) rootView.findViewById(R.id.matches_list);

        mSwipeRefreshLayout = (SwipeRefreshLayout)
                rootView.findViewById(R.id.refresh_layout);

        if(section_number==0)
        {
            setUpMatchesList();

            //GridView grid = (GridView) rootView.findViewById(R.id.gridViewInfoTab);

            // Inicializar el grid view
            //setUpGridViewInfo(grid);

            mMatchPresenter.loadFixture();
        }
        else
            {
                setUpResultsList();
                mMatchPresenter.loadResult();

            }
        return rootView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_grid_match, container, false);
    }

    private void setUpGridViewInfo(GridView /*GridView*/ grid/*, List<Fixture> fixtures*/) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        ArrayList<Fixture> fixtures ;//= GetImagesMock();
        //ArrayList<ImageItem> imageItemsXInventory ;//= GetImagesMock();

        GridViewFixtureAdapter dataAdapter = null;
        switch (section_number) {
            case 0:
                fixtures = GetItemsMock(1);
                //grid.addHeaderView(createHeaderView(6, Products.getTelefonos()));
                dataAdapter = new GridViewFixtureAdapter(getContext(),
                        R.layout.grid_item_fixture, fixtures/*imageItems*/, 1);
                grid.setAdapter(dataAdapter);
                break;
            case 1:
                fixtures = GetItemsMock(2);
                //grid.addHeaderView(createHeaderView(6, Products.getTelefonos()));
                dataAdapter = new GridViewFixtureAdapter(getContext(),
                        R.layout.grid_item_result, fixtures/*imageItems*/, 2);
                grid.setAdapter(dataAdapter);
                break;
            case 2:
                fixtures = GetItemsMock(3);
                dataAdapter = new GridViewFixtureAdapter(getContext(),
                        R.layout.grid_item_fixture, fixtures/*imageItems*/, 3);
                grid.setAdapter(dataAdapter);
                break;
            case 3:
                fixtures = GetItemsMock(4);
                dataAdapter = new GridViewFixtureAdapter(getContext(),
                        R.layout.grid_item_fixture, fixtures/*imageItems*/, 4);
                grid.setAdapter(dataAdapter);
                break;
        }
    }

    public ArrayList<Fixture> GetItemsMock(int typeArticle) {
        ArrayList<Fixture> imagenItemsValidateArray = new ArrayList<>();
        Fixture imagenItemsValidate;
        if(true!=true/*productImageList.size()>0*/)
        {
            /*for (int totalProducts=0;totalProducts<productImageList.size();totalProducts++) {
                ProductImage productImage = productImageList.get(totalProducts);
                //TABLETS

                imagenItemsValidate = new Fixture();
                imagenItemsValidate.setID(totalProducts);
                imagenItemsValidate.setType(productImage.getName());
                imagenItemsValidate.setSelected(true);
                imagenItemsValidate.setImage(iconTablet);
                imagenItemsValidateArray.add(productImage.getTypeProduct(), imagenItemsValidate);

            }*/
        }
        else {
            switch (typeArticle) {
                //TABLETS
                case (1):

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Vino X");
                    imagenItemsValidateArray.add(0, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Whiskey X");
                    imagenItemsValidateArray.add(1, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Vino X");
                    imagenItemsValidateArray.add(2, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Vino X");
                    imagenItemsValidateArray.add(3, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Vino X");
                    imagenItemsValidateArray.add(4, imagenItemsValidate);

                    break;
                //SMARTPHONES
                case (2):

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Maní");
                    imagenItemsValidateArray.add(0, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Maiz");
                    imagenItemsValidateArray.add(1, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Maní");
                    imagenItemsValidateArray.add(2, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Maní");
                    imagenItemsValidateArray.add(3, imagenItemsValidate);

                    imagenItemsValidate = new Fixture();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setType("Maní");
                    imagenItemsValidateArray.add(4, imagenItemsValidate);

                    break;
                /*case (3):
                    Bitmap iconPortatil = BitmapFactory.decodeResource(this.getResources(), R.drawable.tictac);

                    imagenItemsValidate = new ImageItem();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setTitle("Tic Tac");
                    imagenItemsValidate.setSelected(true);
                    imagenItemsValidate.setImage(iconPortatil);
                    imagenItemsValidateArray.add(0, imagenItemsValidate);

                    iconPortatil = BitmapFactory.decodeResource(this.getResources(), R.drawable.tictac);
                    imagenItemsValidate = new ImageItem();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setTitle("Tic Tac");
                    imagenItemsValidate.setSelected(true);
                    imagenItemsValidate.setImage(iconPortatil);
                    imagenItemsValidateArray.add(1, imagenItemsValidate);

                    iconPortatil = BitmapFactory.decodeResource(this.getResources(), R.drawable.tictac);
                    imagenItemsValidate = new ImageItem();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setTitle("Tic Tac");
                    imagenItemsValidate.setSelected(true);
                    imagenItemsValidate.setImage(iconPortatil);
                    imagenItemsValidateArray.add(2, imagenItemsValidate);

                    iconPortatil = BitmapFactory.decodeResource(this.getResources(), R.drawable.tictac);
                    imagenItemsValidate = new ImageItem();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setTitle("Tic Tac");
                    imagenItemsValidate.setSelected(true);
                    imagenItemsValidate.setImage(iconPortatil);
                    imagenItemsValidateArray.add(3, imagenItemsValidate);

                    iconPortatil = BitmapFactory.decodeResource(this.getResources(), R.drawable.tictac);
                    imagenItemsValidate = new ImageItem();
                    imagenItemsValidate.setID(1);
                    imagenItemsValidate.setTitle("Tic Tac");
                    imagenItemsValidate.setSelected(true);
                    imagenItemsValidate.setImage(iconPortatil);
                    imagenItemsValidateArray.add(4, imagenItemsValidate);

                    break;*/
                default:
                    break;

            }
        }
        return imagenItemsValidateArray;
    }


    private void setUpMatchesList() {
        mMatchList.setAdapter(mMatchAdapter);
        mMatchList.setHasFixedSize(true);

    }

    private void setUpResultsList() {
        mMatchList.setAdapter(mResultAdapter);
        mMatchList.setHasFixedSize(true);

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void showFixture(List<Fixture> fixtures) {
        mMatchAdapter.replaceData(fixtures);
    }

    @Override
    public void showResult(List<Result> results) {
        mResultAdapter.replaceData(results);

    }

    @Override
    public void showLoadingState(final boolean show) {
        /*if (getView() == null) {
            return;
        }

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(show);
            }
        });*/
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error, Toast.LENGTH_LONG)
                .show();
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
