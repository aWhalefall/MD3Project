package com.kuaidao.md3project

import android.R
import android.R.attr
import android.R.attr.endColor
import android.R.attr.startColor
import android.R.drawable
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.util.StateSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat.setAlpha
import androidx.fragment.app.Fragment
import cn.beegs.roundview.RoundDrawable
import com.kuaidao.md3project.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val mRadiusTopLeft: Float = 12f
    private val mRadiusTopRight: Float = 12f
    private val mRadiusBottomRight: Float = 0f
    private val mRadiusBottomLeft: Float = 0f


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener {
            with(binding.llRound){
                val drawable=this.background as? RoundDrawable
                //背景色调整
                //drawable?.setBgData(ColorStateList.valueOf(Color.parseColor("#625b71")))
                //圆角动态调整
                //drawable?.cornerRadius = 10f
                //边框宽度和颜色调整
                //drawable?.setStrokeData(10, ColorStateList.valueOf(android.graphics.Color.parseColor("#ff0000")))
                //圆角
                val radii = floatArrayOf(
                    mRadiusTopLeft.toFloat(),
                    mRadiusTopLeft.toFloat(),
                    mRadiusTopRight.toFloat(),
                    mRadiusTopRight.toFloat(),
                    mRadiusBottomRight.toFloat(),
                    mRadiusBottomRight.toFloat(),
                    mRadiusBottomLeft.toFloat(),
                    mRadiusBottomLeft.toFloat()
                )
                drawable?.cornerRadii=radii

                binding.btn1.isEnabled=true
            }
        }



        binding.btn1.background=createSelector(build(), build(Color.parseColor("#ff0000")))

        binding.btn1.setOnClickListener {
             it.isEnabled=!it.isEnabled
        }

    }

    fun listState():StateListDrawable{
        val stateList = StateListDrawable()
        stateList.addState(intArrayOf(attr.state_pressed),build())
        stateList.addState(intArrayOf(-attr.state_pressed),build(Color.parseColor("#ff0000")))
        return stateList
    }


    fun createSelector(normalState:Drawable , pressedState:Drawable):StateListDrawable {
            val bg = StateListDrawable()
            bg.addState(intArrayOf(attr.state_enabled), pressedState)
            bg.addState(StateSet.WILD_CARD, normalState);
            return bg
        }


    fun  build(color:Int=Color.parseColor("#ffff00")):GradientDrawable{
        val drawable = GradientDrawable() // 生成Shape
        drawable.gradientType = GradientDrawable.LINEAR_GRADIENT // 设置矩形
        drawable.setColor(color)// 内容区域的颜色
        drawable.setStroke(1, Color.parseColor("#eeeeee"))
        // 四周描边,描边后四角真正为圆角，不会出现黑色阴影。如果父窗体是可以滑动的，需要把父View设置setScrollCache(false)
        drawable.cornerRadius = 5f // 设置四角都为圆角
        return drawable
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //https://developer.android.google.cn/guide/topics/resources/drawable-resource#StateList
}

