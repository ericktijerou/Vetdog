package erick.mobile.presentation.internal.injection.module.dog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import erick.mobile.domain.Schedulers
import erick.mobile.domain.gateway.InventoryGateway
import erick.mobile.domain.interactor.DogGetByIdUseCase
import erick.mobile.presentation.dog.detail.DogDetailViewModel
import erick.mobile.presentation.internal.injection.scope.DogScope

@Module
internal abstract class DogModule {

    @Module
    companion object {

        @DogScope
        @Provides
        @JvmStatic
        internal fun provideDogGetByIdUseCase(schedulers: Schedulers, inventoryGateway: InventoryGateway): DogGetByIdUseCase {
            return DogGetByIdUseCase(schedulers, inventoryGateway)
        }

        @DogScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             dogFindByTypeUseCase: DogGetByIdUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(DogDetailViewModel::class.java) ->
                            DogDetailViewModel(context, dogFindByTypeUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}