package org.cookpad.rxbroadcaster_app_test.detail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import org.cookpad.rxbroadcaster_app_test.data.RecipeRepository
import org.cookpad.rxbroadcaster_app_test.data.models.Recipe

class RecipePresenter(private val view: View,
                      private val repository: RecipeRepository = RecipeRepository()) : LifecycleObserver {
    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        view.apply {
            showRecipe(repository.get(recipeId))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        compositeDisposable.dispose()
    }

    interface View {
        val recipeId: String
        fun showRecipe(recipe: Recipe)
    }
}