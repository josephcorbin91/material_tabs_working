package info.androidhive.materialtabs.Dashboard.drawer;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.Dashboard.DashboardActivity;
import info.androidhive.materialtabs.Dashboard.adapter.MenuDrawerItemAdapter;
import info.androidhive.materialtabs.Dashboard.model.MenuItemModel;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.utility.PreferenceClass;


public class FragmentDrawer extends Fragment implements View.OnClickListener{
    private Context mContext;
    private RecyclerView recyclerViewMenuItem;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private MenuDrawerItemAdapter adapterMenuItem;
    private View containerView;
    private FragmentDrawerListener drawerListener;
    View layout;
    Toolbar toolBar;
    RelativeLayout layoutParent;
    ImageView imageViewBack,imageViewDrawer;

    private Dialog dialogLogout;

    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mContext=this.getActivity();
        recyclerViewMenuItem = layout.findViewById(R.id.drawerListMenu);

        try{
            layoutParent = layout.findViewById(R.id.layoutParent);

            setUIDesign();

            layout.findViewById(R.id.imgvwCross).setOnClickListener(this);


            recyclerViewMenuItem.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerViewMenuItem, new ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Fragment selectedFragment = null;
/*
                    switch (position){

                        case 0: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 1: selectedFragment = TalkToUsFragment.newInstance();break;
                        case 2: selectedFragment = ProfileFragment.newInstance();break;
                        case 3: selectedFragment = ExploreFragment.newInstance();break;
                        case 4: selectedFragment = SettingsFragment.newInstance();break;
                        case 5: selectedFragment = FaqFragment.newInstance();break;
                        case 6: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 7: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 8: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 9: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 10: selectedFragment = PrivacyPolicy.newInstance();break;
                        case 11: selectedFragment = HomeFeedFragment.newInstance();break;
                        case 12: selectedFragment = HomeFeedFragment.newInstance();break;

                    }
                    ((DashboardActivity)getActivity()).changeToolbars(selectedFragment);
                    imageViewDrawer.setVisibility(View.GONE);
                    imageViewBack.setVisibility(View.VISIBLE);
                    ((DashboardActivity) getActivity()).changeToolbars(selectedFragment);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container_body, selectedFragment);
                    transaction.commit();

                    PreferenceClass.setIntegerPreference(mContext,"SelectedItem",position);
                    adapterMenuItem.notifyDataSetChanged();
                    mDrawerLayout.closeDrawer(layout);
                    */

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));


        }catch (Exception e){
            e.printStackTrace();
        }
        return layout;
    }

    private void setUIDesign(){

        List<MenuItemModel> lstMenuItem = new ArrayList<>();

        lstMenuItem.add(new MenuItemModel(R.string.Messages, R.drawable.drawer_email_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Talk_to_Us, R.drawable.drawer_talk_to_us_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Profile, R.drawable.drawer_profile_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Explore, R.drawable.drawer_explore_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Settings, R.drawable.drawer_setting_icon));
        lstMenuItem.add(new MenuItemModel(R.string.FAQ, R.drawable.drawer_faq_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Connect_On_Facebook, R.drawable.drawer_facebook_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Connect_On_Twitter, R.drawable.drawer_twitter_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Terms_Of_Service, R.drawable.drawer_terms_service_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Contest_Rules, R.drawable.drawer_contest_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Privacy_Policy, R.drawable.drawer_privacy_policy_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Welcome_Screen, R.drawable.drawer_welcome_icon));
        lstMenuItem.add(new MenuItemModel(R.string.Signout, R.drawable.drawer_logout_icon));


        adapterMenuItem = new MenuDrawerItemAdapter(getActivity(), lstMenuItem);


        recyclerViewMenuItem.setAdapter(adapterMenuItem);
        recyclerViewMenuItem.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        toolBar = toolbar;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        imageViewDrawer = (toolbar.findViewById(R.id.imgvwDrawer));
        imageViewBack = (toolbar.findViewById(R.id.imgvwBack));
        imageViewDrawer.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);

        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(layout);
    }

    public boolean isDrawerOpen(){
        if(mDrawerLayout.isDrawerOpen(layout)){
                mDrawerLayout.closeDrawer(layout);
                return true;
        }else{
            return false;
        }
    }




    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position, int id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgvwDrawer:
                    adapterMenuItem.notifyDataSetChanged();
                    mDrawerLayout.openDrawer(containerView);
                break;
            case R.id.imgvwCross:
                adapterMenuItem.notifyDataSetChanged();
                if(mDrawerLayout.isDrawerOpen(layout)){
                    mDrawerLayout.closeDrawer(layout);
                }
                break;
            case R.id.imgvwBack:
                Fragment selectedFragment = null;
                //selectedFragment = HomeFeedFragment.newInstance();
                imageViewDrawer.setVisibility(View.VISIBLE);
                imageViewBack.setVisibility(View.GONE);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_body, selectedFragment);
                transaction.commit();

                PreferenceClass.setIntegerPreference(mContext,"SelectedItem",-1);
                adapterMenuItem.notifyDataSetChanged();
                mDrawerLayout.closeDrawer(layout);
                break;
            default:
                break;
        }
    }

}
