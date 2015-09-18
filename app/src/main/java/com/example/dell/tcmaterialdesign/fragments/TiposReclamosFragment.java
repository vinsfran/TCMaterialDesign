package com.example.dell.tcmaterialdesign.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.tcmaterialdesign.MainActivity;
import com.example.dell.tcmaterialdesign.R;
import com.example.dell.tcmaterialdesign.adapters.TiposReclamosAdapter;
import com.example.dell.tcmaterialdesign.domain.TiposReclamos;
import com.example.dell.tcmaterialdesign.interfaces.RecyclerViewOnClickListenerHack;

import java.util.List;


public class TiposReclamosFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<TiposReclamos> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tipos_reclamos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                TiposReclamosAdapter adapter = (TiposReclamosAdapter) mRecyclerView.getAdapter();
                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<TiposReclamos> listAux = ((MainActivity) getActivity()).getSetTiposReclamosList(10);
                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });
        mRecyclerView.addOnItemTouchListener(new ReclyclerViewTouchListener(getActivity(), mRecyclerView, this));


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((MainActivity) getActivity()).getSetTiposReclamosList(10);
        TiposReclamosAdapter adapter = new TiposReclamosAdapter(getActivity(), mList);
        // adapter.setRecyclerViewOnClickListenerHack(this);

        mRecyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onClickListener(View view, int position) {

        Toast.makeText(getActivity(), "onClickListener()" + position + mList.get(position).getNombreTipoReclamo(), Toast.LENGTH_SHORT).show();
        /*  TiposReclamosAdapter adapter = (TiposReclamosAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position); */
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onLongPressClickListener()" + position + mList.get(position).getNombreTipoReclamo(), Toast.LENGTH_SHORT).show();
        /*TiposReclamosAdapter adapter = (TiposReclamosAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);*/
    }

    private static class ReclyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public ReclyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());
                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv, rv.getChildAdapterPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());
                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv, rv.getChildAdapterPosition(cv));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}