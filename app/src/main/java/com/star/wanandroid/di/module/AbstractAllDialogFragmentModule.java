package com.star.wanandroid.di.module;

import com.star.wanandroid.di.component.BaseDialogFragmentComponent;
import com.star.wanandroid.ui.main.fragment.SearchDialogFragment;
import com.star.wanandroid.ui.main.fragment.UsageDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BaseDialogFragmentComponent.class)
public abstract class AbstractAllDialogFragmentModule {

    @ContributesAndroidInjector(modules = SearchDialogFragmentModule.class)
    abstract SearchDialogFragment contributesSearchDialogFragmentInject();

    @ContributesAndroidInjector(modules = UsageDialogFragmentModule.class)
    abstract UsageDialogFragment contributesUsageDialogFragmentInject();

}
