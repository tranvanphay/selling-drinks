package com.example.lenovo.duan1.NguoiDungFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThanhToanNguoiDungFragment extends Fragment {
    TabLayout tabLayout;


    public ThanhToanNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_toan_nguoi_dung, container, false);
        tabLayout = view.findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Giỏ hàng"));
        tabLayout.addTab(tabLayout.newTab().setText("Lịch sử giao dịch"));

        loadFragment(new GioHangNguoiDungFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()){
                    case 0:
                        fragment = new GioHangNguoiDungFragment();
                        loadFragment(fragment);
                        break;
                    case 1:
                        fragment = new LichSuGiaoDichFragment();
                        loadFragment(fragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


                                         //        final MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager());
//        viewPager.setAdapter(adapter);

//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//

        return view;
    }

//    class MyFragmentAdapter extends FragmentStatePagerAdapter {
//        public MyFragmentAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Fragment fragment;
//            switch (position) {
//                case 0:
//                    fragment = new ChuaGiaoFragment();
//                    break;
//                case 1:
//                    fragment = new DaGiaoFragment();
//                    break;
//                default:
//                    return null;
//            }
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 2;
//        }
//    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_containerThanhToan, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
