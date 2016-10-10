/*
 * The MIT License
 *
 * Copyright (c) 2013-2016 reark project contributors
 *
 * https://github.com/reark/reark/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.reark.rxgithubapp.basic.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reark.reark.network.fetchers.UriFetcherManager;
import io.reark.rxgithubapp.basic.data.stores.GitHubRepositorySearchStore;
import io.reark.rxgithubapp.basic.data.stores.GitHubRepositoryStore;
import io.reark.rxgithubapp.basic.data.stores.NetworkRequestStatusStore;
import io.reark.rxgithubapp.basic.data.stores.StoreModule;
import io.reark.rxgithubapp.basic.data.stores.UserSettingsStore;
import io.reark.rxgithubapp.basic.network.FetcherModule;
import io.reark.rxgithubapp.shared.data.DataFunctions;

@Module(includes = { FetcherModule.class, StoreModule.class })
public final class DataStoreModule {

    @Provides
    public DataFunctions.FetchAndGetGitHubRepository provideFetchAndGetGitHubRepository(DataLayer dataLayer) {
        return dataLayer::fetchAndGetGitHubRepository;
    }

    @Provides
    public DataFunctions.GetGitHubRepository provideGetGitHubRepository(DataLayer dataLayer) {
        return dataLayer::getGitHubRepository;
    }

    @Provides
    public DataFunctions.GetGitHubRepositorySearch provideGitHubRepositorySearch(DataLayer dataLayer) {
        return dataLayer::fetchAndGetGitHubRepositorySearch;
    }

    @Provides
    public DataFunctions.GetUserSettings provideGetUserSettings(DataLayer dataLayer) {
        return dataLayer::getUserSettings;
    }

    @Provides
    public DataFunctions.SetUserSettings provideSetUserSettings(DataLayer dataLayer) {
        return dataLayer::setUserSettings;
    }


    @Provides
    @Singleton
    public DataLayer provideServiceDataLayer(UriFetcherManager fetcherManager,
                                             NetworkRequestStatusStore networkRequestStatusStore,
                                             GitHubRepositoryStore gitHubRepositoryStore,
                                             GitHubRepositorySearchStore gitHubRepositorySearchStore,
                                             UserSettingsStore userSettingsStore) {
        return new DataLayer(fetcherManager,
                             networkRequestStatusStore,
                             gitHubRepositoryStore,
                             gitHubRepositorySearchStore,
                             userSettingsStore);
    }

}
