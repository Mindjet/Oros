package io.github.mindjet.oros.ui

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.mindjet.oros.Constant
import io.github.mindjet.oros.R
import io.github.mindjet.oros.ext.getHeroRole
import io.github.mindjet.oros.ext.getLanguageIndex
import io.github.mindjet.oros.ext.load
import io.github.mindjet.oros.ext.withSpace
import io.github.mindjet.oros.model.HeroBrief
import kotlinx.android.synthetic.main.fragment_hero_brief.view.*

class HeroBriefBottomSheet : BottomSheetDialogFragment() {

    private val languageIndex = getLanguageIndex()

    companion object {
        fun newInstance(itemData: HeroBrief): HeroBriefBottomSheet {
            val instance = HeroBriefBottomSheet()
            val bundle = Bundle()
            bundle.putSerializable(Constant.ARG_HERO_BRIEF, itemData)
            instance.arguments = bundle
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val heroBrief = arguments?.getSerializable(Constant.ARG_HERO_BRIEF) as HeroBrief
        val view = inflater.inflate(R.layout.fragment_hero_brief, container, false)
        view.apply {
            iv_hero_avatar.load(heroBrief.avatar)
            tv_hero_real_name.text = heroBrief.realName[languageIndex].withSpace()
            tv_hero_bio.text = heroBrief.bio[languageIndex].withSpace()
            tv_hero_age.text = getString(R.string.hero_age, heroBrief.age).withSpace()
            tv_hero_base.text = getString(R.string.hero_base, heroBrief.baseOfOperation[languageIndex]).withSpace()
            tv_hero_affiliation.text = getString(R.string.hero_affiliation, heroBrief.affiliation[languageIndex]).withSpace()
            tv_hero_role.text = heroBrief.role.getHeroRole().withSpace()
        }
        return view
    }

}