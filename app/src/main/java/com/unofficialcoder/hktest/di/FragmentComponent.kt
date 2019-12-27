package com.unofficialcoder.hktest.di
import com.unofficialcoder.hktest.ui.home.photo.PhotoFragment
import com.unofficialcoder.hktest.ui.home.post.PostFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: PhotoFragment)

    fun inject(fragment: PostFragment)
}