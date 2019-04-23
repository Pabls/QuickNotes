package com.ar4i.quicknotes.presentation.base.views;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ar4i.quicknotes.BuildConfig;
import com.ar4i.quicknotes.R;
import com.ar4i.quicknotes.app.App;
import com.ar4i.quicknotes.app.di.components.ApplicationComponent;
import com.ar4i.quicknotes.presentation.base.presenter.IPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements IMvpView {

    // region========================================Fields=========================================

    FrameLayout rootLayout;

    // endregion-------------------------------------Fields-----------------------------------------

    //==========================================start Lifecycle=====================================

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rootLayout = getActivity().findViewById(android.R.id.content);

        if(getPresenter() != null)
            getPresenter().attachView(this);

        enableStrictMode();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().detachView();
    }

    //-------------------------------------------end Lifecycle--------------------------------------

    //==========================================start implements MvpView============================

    @Override
    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoad() {
        View.inflate(getActivity(), R.layout.view_progress_bar, rootLayout);
    }

    @Override
    public void hideLoad() {
        if (rootLayout != null)
            rootLayout.removeViewAt(rootLayout.getChildCount() - 1);
    }

    //-------------------------------------------end -----------------------------------------------


    //==========================================start Protected methods=============================

    protected ApplicationComponent getComponent() {
        return App.getApplicationComponent();
    }

    //-------------------------------------------end Protected methods------------------------------


    //==========================================start Abstract methods==============================

    protected abstract void inject();

    protected abstract int getLayoutId();

    protected abstract IPresenter<IMvpView> getPresenter();

    //-------------------------------------------end Abstract methods-------------------------------

    // region========================================Private methods================================

    private void enableStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build());
        }
    }

    // endregion-------------------------------------Private methods--------------------------------
}