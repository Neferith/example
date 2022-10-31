package com.frontparissportifs.ui.detail

import com.frontparissportifs.ui.result.IResultContract
import javax.inject.Inject

class DetailPresenter  @Inject constructor(val model: IDetailContract.Model) : IDetailContract.Presenter {
    var view: IDetailContract.View? = null

    override fun attach(view: IDetailContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }
}