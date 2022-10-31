package com.frontparissportifs.ui.detail

import androidx.lifecycle.LiveData
import com.frontparissportifs.features.search.IBaseContract
import com.frontparissportifs.model.Team
import com.frontparissportifs.utils.DataState

interface IDetailContract {

    interface View: IBaseContract.View

    interface Model: IBaseContract.Model {

    }

    interface Presenter: IBaseContract.Presenter<View>{

    }

}