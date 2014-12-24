package com.tcapps.hearthtools.module;

import dagger.Module;
import com.tcapps.hearthtools.fragment.DeckListFragment;

@Module(injects = {DeckListFragment.class}, library = true, complete = false)
public class FragmentModule {

    public FragmentModule() {

    }
}
