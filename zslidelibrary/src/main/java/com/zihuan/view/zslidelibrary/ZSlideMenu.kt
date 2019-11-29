package com.zihuan.view.zslidelibrary

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.zihuan.library.R

class ZSlideMenu : LinearLayout {

    private var menuHelper = SlideMenuUtils()
    //    阴影部分
    private lateinit var shadowLeft: View

//    constructor(context: Context) : super(context) {
//        createDefaultView(null)
//    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        createDefaultView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        createDefaultView(attrs)
    }

    var mClickable = false
    /**生成阴影**/
    fun createDefaultView(attrs: AttributeSet) {
//        横向布局
        orientation = HORIZONTAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZHSlideMenuStyle)
        var width = typedArray.getInteger(R.styleable.ZHSlideMenuStyle_su_shadow_width, 50)
//        屏蔽点击无效 bug
        mClickable = typedArray.getBoolean(R.styleable.ZHSlideMenuStyle_su_shadow_clickable, true)
//        阴影
        var shadowColor = typedArray.getColor(R.styleable.ZHSlideMenuStyle_su_shadow_color, Color.parseColor("#99000000"))
        shadowLeft = FrameLayout(context)
        var dip = (width * resources.displayMetrics.density).toInt()
        shadowLeft.layoutParams = FrameLayout.LayoutParams(dip, ViewGroup.LayoutParams.MATCH_PARENT)
        shadowLeft.setBackgroundColor(shadowColor)
        shadowLeft.isClickable = mClickable
        addView(shadowLeft)
        typedArray.recycle()
        setView()
    }

    fun setView() {
        menuHelper.setView(this, shadowLeft)
        shadowLeft.setOnClickListener {
            toggle()
        }
    }


    fun disableShadowClick() {
        shadowLeft.isClickable = false
    }

    var isOpen = false
    //    切换开关
    fun toggle() {
        isOpen = !isOpen
        menuHelper.toggle()
    }

    fun openView() {
        isOpen = true
        menuHelper.showCategoryView()
    }

    fun closeView() {
        if (isOpen) menuHelper.hideCategoryView()
        isOpen = false
    }


}