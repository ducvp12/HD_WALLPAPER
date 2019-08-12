package com.example.asshoanthien.dnhonthin.utills;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.asshoanthien.dnhonthin.R;

public class ViewHelper {
    // fun này để chuyển fragment
    public static void switchFragment(FragmentActivity context, Fragment fragment) {
        String name = fragment.getClass().getName();
        // lấy ra tên fragment được chuyển tới -> mục đích để cho vào stack. có thể log ra
        Log.d("switchFragment", "fragment name: " + name);

        // Lớp FragmentTransaction dùng để chuyển trang và cung cấp hiệu ứng chuyển trang
        FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        // Thay thế fragment vào frameLayout của main activity (có id = container)
        ft.replace(R.id.container, fragment);

        // add vào stack của fragment manager
        ft.addToBackStack(name);

        // thực thi chuyển fragment
        ft.commit();
    }
}
