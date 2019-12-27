package com.unofficialcoder.hktest.di

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.unofficialcoder.hktest.data.repository.PhotoRepository
import com.unofficialcoder.hktest.data.repository.PostRepository
import com.unofficialcoder.hktest.ui.base.BaseFragment
import com.unofficialcoder.hktest.ui.home.photo.PhotoAdapter
import com.unofficialcoder.hktest.ui.home.photo.PhotoViewModel
import com.unofficialcoder.hktest.ui.home.post.PostAdapter
import com.unofficialcoder.hktest.ui.home.post.PostViewModel
import com.unofficialcoder.hktest.utils.NetworkHelper
import com.unofficialcoder.hktest.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providePhotoViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        dummyRepository: PhotoRepository
    ): PhotoViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(PhotoViewModel::class) {
                PhotoViewModel( compositeDisposable, networkHelper, dummyRepository)
            }
        ).get(PhotoViewModel::class.java)

    @Provides
    fun providePostViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        dummyRepository: PostRepository
    ): PostViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(PostViewModel::class) {
                PostViewModel( compositeDisposable, networkHelper, dummyRepository)
            }
        ).get(PostViewModel::class.java)

    @Provides
    fun providePhotoAdapter() = PhotoAdapter (fragment.lifecycle, ArrayList())

    @Provides
    fun providePostAdapter() = PostAdapter (fragment.lifecycle, ArrayList())
}