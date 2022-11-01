package com.frontparissportifs.ui.autocomplete

import androidx.lifecycle.LiveData
import com.frontparissportifs.ui.base.BaseContract
import com.frontparissportifs.utils.DataState

interface AutocompleteContract : BaseContract {

    interface View : BaseContract.View {

        /**
         * Selectionne le [keyword]. Cette methode va prevenir les autres fragments.
         */
        fun updateCurrentKeywordSelected(keyword: String)

        /**
         * Demander la fermeture du clavier et perd le focus pour l'input de recherche
         */
        fun executeCloseKeyboard()

    }

    interface Model : BaseContract.Model {

        /**
         * Retourne toutes les leagues de foot
         */
        fun allSoccerLeagues(onFinishedListener: OnFinishedListener<List<String>>)

        /**
         * Vérifie qu'une league de foot existe par son [leagueName]
         */
        fun fetchLeagueExist(leagueName:String, onFinishedListener: OnFinishedListener<Boolean>)


        interface OnFinishedListener<T> {
            fun onFinished(result: DataState<T>)
        }

    }

    interface Presenter : BaseContract.Presenter<View> {

        /**
         * Pointe sur la liste de toutes les leagues de foot, pour l'autocomplétion
         */
        val allLeaguesNamesState: LiveData<DataState<List<String>>>

        /**
         * L'utilisateur a selectionné une [item] dans l'autocomplétion.
         * La recherche sera effectuée.
         */
        fun onChooseItemInAutocompleteList(item: String)

        /**
         * L'utilisateur a effectué une recherche depuis son clavier [keyword].
         * Si le [keyword] correspond à une league de foot, la recherche sera effectuée.
         * Sinon, il faudrait gérer une erreur
         */
        fun performSearch(keyword: String)

    }

}