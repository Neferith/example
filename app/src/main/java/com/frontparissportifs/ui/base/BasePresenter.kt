package com.frontparissportifs.ui.base

import com.frontparissportifs.features.search.IBaseContract

class BasePresenter<V : IBaseContract.View, M : IBaseContract.Model>: IBaseContract.Presenter<V,M> {

     var view: V? = null
     var model: M? = null

     override fun attach(view: V, model:M) {
         this.view = view
         this.model = model
     }

     override fun detach() {
         this.view = null
         this.model = null
     }

 }