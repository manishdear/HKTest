package com.unofficialcoder.hktest.di.components


import com.unofficialcoder.hktest.di.ApplicationComponent
import com.unofficialcoder.hktest.di.ViewModelScope
import com.unofficialcoder.hktest.di.modules.ViewHolderModule
import com.unofficialcoder.hktest.ui.home.photo.PhotoItemViewHolder
import com.unofficialcoder.hktest.ui.home.post.PostItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: PhotoItemViewHolder)

    fun inject(viewHolder: PostItemViewHolder)
}