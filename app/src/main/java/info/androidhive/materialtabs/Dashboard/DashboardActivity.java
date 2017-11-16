package info.androidhive.materialtabs.Dashboard;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.camerafragment.CameraFragment;
import com.github.florent37.camerafragment.configuration.Configuration;
import com.github.florent37.camerafragment.listeners.CameraFragmentResultAdapter;

import java.io.File;

import info.androidhive.materialtabs.Dashboard.drawer.FragmentDrawer;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.CustomViewIconTextTabsActivity;
import info.androidhive.materialtabs.constant.Constant;
import info.androidhive.materialtabs.utility.PreferenceClass;

import static android.Manifest.permission.CAMERA;

public class DashboardActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    public static Toolbar mToolbar;
    private static final int REQUEST_ACCESS_CAMERA = 23;
    private String mostRecentPhotoPath;

    private FragmentDrawer drawerFragment;
    private Context mContext;
    private ImageView mLeftMenuIconBack,mLeftMenuIconDrawerMenu,    mRightMenuIconProfile,mRightMenuIconSettings,mRightMenuIconCreate,
    mRightMenuIconSend;
    private TextView mRightMenuSelectButton;
    private TabLayout standardTabLayout;
    private LinearLayout pictureLayout,pictureTakenTabLayout,tabLayoutPictureIcon;
    private View bottomDashboardDivider;
    private TextView mToolbarTitle;
    private ImageView takePicutreFromSelection;
    private Fragment currentFragment=null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_dashboard);
        getSupportFragmentManager().beginTransaction().add(R.id.content_fragment_placeholder, new CustomViewIconTextTabsActivity(), null).commit();


        mContext = this;

        PreferenceClass.setBooleanPreference(mContext, Constant.User.IS_LOGIN,true);
        setActionBar();

        setupTabLayout();

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

    }

    private void setupTabLayout() {
        final TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        this.standardTabLayout = mTabLayout;
        this.pictureLayout = (LinearLayout)findViewById(R.id.tabLayoutPictureFragment);
        this.pictureTakenTabLayout = (LinearLayout)findViewById(R.id.tabLayoutPictureTakenFragment);
        this.bottomDashboardDivider = (View)findViewById(R.id.activity_dashboard_bottom_divider);
        this.tabLayoutPictureIcon = (LinearLayout) findViewById(R.id.tabLayoutPictureIcon);
        this.takePicutreFromSelection = (ImageView)findViewById(R.id.takePicutreFromSelection);
        onTabSelection(0);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabSelection(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelection(tab.getPosition());
            }
        });
    }


    private void onTabSelection(int position) {
        /*
        Fragment selectedFragment = null;

        switch (position) {
            case 0:
                selectedFragment = HomeFeedFragment.newInstance();
                break;
            case 1:
                selectedFragment = BrandChallengeAndBrandFeedFragment.newInstance();
                break;
            case 2:
                selectedFragment = CreateChallengeProposeQuestionFragment.newInstance();
                break;
            case 3:
                selectedFragment = NotificationFragment.newInstance();
                break;
            case 4:
                selectedFragment = ExploreFragment.newInstance();
                break;
            default:

                break;
        }

        if(currentFragment!=null && currentFragment.getClass().equals(selectedFragment.getClass())){

        }
        else if(selectedFragment!=null ){
            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_body, selectedFragment);
            transaction.commit();
            currentFragment= selectedFragment;
          //  changeToolbars(selectedFragment);

        }
        */
    }
    private void setActionBar() {
        try{
            mToolbar = (Toolbar) findViewById(R.id.toolbarDashboard);
            mToolbarTitle = (TextView)mToolbar.findViewById(R.id.txtTitleToolbar);
            mLeftMenuIconBack = (ImageView)mToolbar.findViewById(R.id.imgvwBack);
            mLeftMenuIconDrawerMenu = (ImageView)mToolbar.findViewById(R.id.imgvwDrawer);
            mRightMenuIconSend = (ImageView)mToolbar.findViewById(R.id.imgvwRightSideSend);
            mRightMenuIconProfile = (ImageView)mToolbar.findViewById(R.id.imgvwRightSideProfile);
            mRightMenuIconSettings = (ImageView)mToolbar.findViewById(R.id.imgvwRightSideSettings);
            mRightMenuIconCreate = (ImageView)mToolbar.findViewById(R.id.imgvwRightSideCreate);
            mRightMenuSelectButton = (TextView)mToolbar.findViewById(R.id.imgvwRightSideSelectButton);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDrawerItemSelected(View view, int position, int id) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    public boolean allowCameraAccess() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        requestPermissions(new String[]{CAMERA}, REQUEST_ACCESS_CAMERA);

        return false;
    }

    private ImageView cameraIcon, switchCameraBackToFront,flashIcon,downloadIcon,proceedIcon;

    @RequiresPermission(CAMERA)
    public void loadCameraFragmnet(){
        final CameraFragment cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_body, cameraFragment)
                .commit();

        pictureLayout.setVisibility(View.VISIBLE);
        tabLayoutPictureIcon.setVisibility(View.GONE);
        mLeftMenuIconBack.setVisibility(View.VISIBLE);
        mLeftMenuIconDrawerMenu.setVisibility(View.GONE);
        mRightMenuIconSend.setVisibility(View.GONE);
        mRightMenuIconProfile.setVisibility(View.GONE);
        mRightMenuIconSettings.setVisibility(View.GONE);
        mRightMenuSelectButton.setVisibility(View.GONE);

        this.bottomDashboardDivider.setVisibility(View.GONE);
        this.pictureLayout.setVisibility(View.VISIBLE);
        final File root = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

        this.standardTabLayout.setVisibility(View.GONE);
        this.cameraIcon =  (ImageView)findViewById(R.id.camera_fragment_camera_icon);
        this.switchCameraBackToFront =  (ImageView)findViewById(R.id.camera_fragment_reboot_icon);
        this.flashIcon =  (ImageView)findViewById(R.id.camera_fragment_flash_icon);
        this.downloadIcon =  (ImageView)findViewById(R.id.photo_taken_fragment_download_icon);
        this.proceedIcon=  (ImageView)findViewById(R.id.photo_taken_fragment_proceed_icon);

        this.proceedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  loadFragment(WriteAboutYourPost.newInstance());
                pictureLayout.setVisibility(View.GONE);
                pictureTakenTabLayout.setVisibility(View.GONE);
            }
        });
        cameraIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultAdapter() {
                            @Override
                                                               public void onVideoRecorded(String filePath) {

                                                                   Toast.makeText(getBaseContext(), "onVideoRecorded " + filePath, Toast.LENGTH_SHORT).show();
                                                                    pictureLayout.setVisibility(View.GONE);
                                                                   pictureTakenTabLayout.setVisibility(View.VISIBLE);
                                                               }

                                                               @Override
                                                               public void onPhotoTaken(byte[] bytes, String filePath) {
                                                                   Toast.makeText(getBaseContext(), "onPhotoTaken " + filePath, Toast.LENGTH_SHORT).show();
                                                                   pictureLayout.setVisibility(View.GONE);
                                                                   mostRecentPhotoPath= filePath;
                                                                   pictureTakenTabLayout.setVisibility(View.VISIBLE);

                                                               }
                                                           },
                            root.getAbsolutePath(),
                            "photo0");
                }



        });
        switchCameraBackToFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraFragment.switchCameraTypeFrontBack();
            }
        });
        flashIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraFragment.toggleFlashMode();
            }
        });


        mToolbarTitle.setText("");

    }
    public void loadFragment(Fragment fragment){



            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_body, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

       // changeToolbars(fragment);

    }


    @Override
    public void onBackPressed() {
        if (!drawerFragment.isDrawerOpen()) {
            super.onBackPressed();
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_body);
       //     changeToolbars(f);

        }
    }


}