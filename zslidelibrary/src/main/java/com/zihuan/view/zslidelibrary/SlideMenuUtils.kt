package com.zihuan.view.zslidelibrary

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

/**
 * 侧滑页面的动画
 */
class SlideMenuUtils {
    //筛选弹出动画 做成动画文件
    private lateinit var mCategoryMoveIn: TranslateAnimation
    private lateinit var mCategoryMoveOut: TranslateAnimation
    private lateinit var mCategoryShadowFadeIn: AlphaAnimation
    private lateinit var mCategoryShadowFadeOut: AlphaAnimation
    private lateinit var mRootView: View
    private lateinit var mShadow: View

    init {
        initCategoryFilter()
    }

    //筛选部分初始化
    private fun initCategoryFilter() {
        val SELF = Animation.RELATIVE_TO_SELF
        //X轴初始位置 X轴移动的结束位置 y轴开始位置 y轴移动后的结束位置
        mCategoryMoveIn = TranslateAnimation(SELF, 1.0f, SELF, 0f, SELF, 0.0f, SELF, 0.0f)
                .apply {
                    duration = 300
                    fillAfter = true
                }
        mCategoryMoveOut = TranslateAnimation(SELF, 0.0f, SELF, 1f, SELF, 0.0f, SELF, 0.0f)
                .apply {
                    duration = 300
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {

                        }

                        override fun onAnimationEnd(animation: Animation) {
                            mRootView.visibility = View.GONE
                        }

                        override fun onAnimationRepeat(animation: Animation) {

                        }
                    })
                }
        mCategoryShadowFadeIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 50
            startOffset = 300
            //动画结束后，控件停留在执行后的状态
            fillAfter = true
        }
        mCategoryShadowFadeOut = AlphaAnimation(1.0f, 0.0f).apply {
            duration = 50
            //动画结束后，控件停留在执行后的状态
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {

                }

                override fun onAnimationEnd(animation: Animation) {
                    mRootView.startAnimation(mCategoryMoveOut)
                }

                override fun onAnimationRepeat(animation: Animation) {

                }
            })
        }
    }

    fun setView(rootView: View, shadow: View) {
        mRootView = rootView
        mShadow = shadow
    }

    /**
     * 显示
     */
    fun showCategoryView() {
        mViewIsOpen = true
        mRootView.visibility = View.VISIBLE
        mRootView.startAnimation(mCategoryMoveIn)
        mShadow.startAnimation(mCategoryShadowFadeIn)
    }

    /**
     * 隐藏
     */
    fun hideCategoryView() {
        mViewIsOpen = false
        mShadow.startAnimation(mCategoryShadowFadeOut)
    }

    private var mViewIsOpen = false
    /***
     * 切换开关
     */
    fun toggle() = if (mViewIsOpen) hideCategoryView() else showCategoryView()


}
