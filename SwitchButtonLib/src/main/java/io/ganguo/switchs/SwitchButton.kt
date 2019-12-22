package io.ganguo.switchs

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.FILTER_BITMAP_FLAG
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.jlertele.switchs.R


/**
 * <pre>
 *     author : leo
 *     time   : 2019/12/05
 *     desc   :
 * </pre>
 * @property thumbBgShadowColor  滑块阴影背景颜色
 * @property thumbShadowDx 滑块阴影x轴偏移量
 * @property thumbShadowDy 滑块阴影y轴偏移量
 * @property thumbShadowRadius 滑块阴影半径
 * @property thumbOnBgColor on状态滑块背景颜色
 * @property thumbOffBgColor off状态滑块背景颜色
 * @property trackOffTransitBgColor 开关状态切换背景过渡动画颜色
 * @property trackOnBgColor 开启状态背景颜色
 * @property trackOffBgColor 关闭状态背景颜色
 * @property thumbSize 滑块大小（直径)
 * @property thumbRadius 滑块半径
 * @property trackWidth 开关背景宽度
 * @property trackHeight 开关背景高度
 * @property trackBgRadius 开关背景圆角半径
 * @property isOpened 开关状态
 * @property isOpenedLast 上一次开关状态
 * @property isEnableThumbShadow 是否开启按钮阴影
 * @property toggleListener 开关状态回调
 * @property trackOnPaint 背景开启状态画笔
 * @property trackOffPaint 背景关闭状态画笔
 * @property thumbShadowPaint 滑块阴影画笔
 * @property thumbOnBgPaint 滑块开启状态颜色画笔
 * @property thumbOffBgPaint 滑块关闭状态颜色画笔
 * @property trackPath  背景绘制路径
 * @property thumbPath 滑块绘制路径
 * @property thumbShadowPaint 阴影绘制路径
 * @property trackRectF 背景绘制范围
 * @property thumbOffCenterX 滑块关闭时，绘制中心X轴
 * @property thumbOffCenterX 滑块开启时，绘制中心X轴
 * @property thumbCenterY 滑块所在Y轴
 * @property thumbTotalOffset 滑块可以移动的总距离
 * @property thumbOffsetParent 滑动移动距离的百分比，用于处理滑动过渡动画
 * @property thumbAnimatorDuration 过渡动画时长
 * @property bgAlpha 滑块和背景透明度，用于过渡动画计算，使动画过渡更自热
 * @property thumbAnimator 滑块动画处理对象
 * @property animatedFraction 动画当前播放进度
 *
 */
open class SwitchButton : View {
    @JvmOverloads
    constructor(context: Context?) : this(context, null)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs, defStyleAttr)
    }


    /**
     * View 初始化
     */
    protected open fun initView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        initAttributes(attrs)
        initButtonState()
        initPaint()
    }


    @ColorInt
    protected var thumbBgShadowColor: Int = Color.BLACK
    protected var thumbShadowDy: Int = 14
    protected var thumbShadowDx: Int = 8
    protected var thumbShadowRadius: Int = 16

    @ColorInt
    protected var thumbOnBgColor: Int = Color.WHITE
    @ColorInt
    protected var thumbOffBgColor: Int = Color.WHITE
    @ColorInt
    protected var trackOffTransitBgColor: Int = Color.DKGRAY
    @ColorInt
    protected var trackOnBgColor: Int = Color.GREEN
    @ColorInt
    protected var trackOffBgColor: Int = Color.LTGRAY

    @Dimension
    protected var thumbRadius: Int = 0
    @Dimension
    protected var thumbSize: Int = thumbRadius * 2
    @Dimension
    protected var trackWidth: Int = 0
    @Dimension
    protected var trackHeight: Int = 0
    @Dimension
    protected var trackBgRadius: Int = 0

    protected var isOpened: Boolean = false
    protected var isOpenedLast: Boolean = !isOpened
    protected var isEnableThumbShadow: Boolean = false

    protected var trackOnPaint: Paint = Paint()
    protected var trackOffPaint: Paint = Paint()
    protected var thumbShadowPaint = Paint()
    protected var thumbOnBgPaint: Paint = Paint()
    protected var thumbOffBgPaint: Paint = Paint()
    protected var trackPath: Path = Path()
    protected var thumbPath: Path = Path()
    protected var thumbShadowPath: Path = Path()
    protected var trackRectF = RectF()

    protected var thumbOffCenterX: Float = 0f
    protected var thumbOnCenterX: Float = 0f
    protected var thumbCenterY: Float = 0f
    protected var thumbTotalOffset: Float = 0f
    protected var thumbOffsetParent: Float = 0f
    protected var thumbAnimatorDuration: Int = 300
    protected var thumbShadowSize: Int = 0
    protected var bgAlpha: Int = 0
    protected val thumbAnimator: ValueAnimator by lazy {
        newThumbAnimator()
    }
    private var animatedFraction: Float = 1f
    private var toggleListener: OnSwitchChangeListener? = null


    /**
     * @property DEFAULT_THUMB_RADIUS 默认滑块大小半径
     * @property DEFAULT_TRACK_HEIGHT 默认控件背景高度
     * @property DEFAULT_TRACK_WIDTH 默认控件宽度
     */
    companion object {
        private const val DEFAULT_THUMB_RADIUS: Int = 42
        private const val DEFAULT_TRACK_WIDTH: Int = 150
        private const val DEFAULT_TRACK_HEIGHT: Int = 90
    }


    /**
     * 创建滑块动画
     * @return ValueAnimator
     */
    protected open fun newThumbAnimator(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = thumbAnimatorDuration.toLong()
        valueAnimator.addUpdateListener {
            animatedFraction = it.animatedFraction
            thumbOffsetParent = it.animatedValue as Float
            bgAlpha = ((it.animatedValue as Float) * 255).toInt()
            postInvalidate()
        }
        return valueAnimator
    }

    /**
     * 初始化控件自定义属性
     * @param attrs AttributeSet?
     */
    private fun initAttributes(attrs: AttributeSet?) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton)
        thumbBgShadowColor =
            array.getColor(R.styleable.SwitchButton_thumbBgShadowColor, thumbBgShadowColor)
        thumbOnBgColor = array.getColor(R.styleable.SwitchButton_thumbOnBgColor, thumbOnBgColor)
        thumbOffBgColor = array.getColor(R.styleable.SwitchButton_thumbOffBgColor, -1)
        trackOffTransitBgColor =
            array.getColor(R.styleable.SwitchButton_trackOffTransitBgColor, trackOffTransitBgColor)
        trackOnBgColor = array.getColor(R.styleable.SwitchButton_trackOnBgColor, trackOnBgColor)
        trackOffBgColor = array.getColor(R.styleable.SwitchButton_trackOffBgColor, trackOffBgColor)
        if (thumbOffBgColor == -1) {
            thumbOffBgColor = thumbOnBgColor
        }
        thumbRadius = array.getDimensionPixelOffset(
            R.styleable.SwitchButton_thumbRadius,
            DEFAULT_THUMB_RADIUS
        )
        trackWidth =
            array.getDimensionPixelOffset(R.styleable.SwitchButton_trackWidth, DEFAULT_TRACK_WIDTH)
        trackHeight =
            array.getDimensionPixelOffset(
                R.styleable.SwitchButton_trackHeight,
                DEFAULT_TRACK_HEIGHT
            )
        trackBgRadius =
            array.getDimensionPixelOffset(
                R.styleable.SwitchButton_trackBgRadius,
                -1
            )
        if (trackBgRadius == -1) {
            trackBgRadius = trackHeight / 2
        }

        thumbAnimatorDuration =
            array.getInt(R.styleable.SwitchButton_thumbAnimatorDuration, thumbAnimatorDuration)
        thumbSize = thumbRadius * 2

        thumbShadowDx = array.getInt(R.styleable.SwitchButton_thumbShadowDx, thumbShadowDx)
        thumbShadowDy = array.getInt(R.styleable.SwitchButton_thumbShadowDy, thumbShadowDy)
        thumbShadowRadius =
            array.getInt(R.styleable.SwitchButton_thumbShadowRadius, thumbShadowRadius)

        isOpened = array.getBoolean(R.styleable.SwitchButton_isOpen, isOpened)
        isEnableThumbShadow = array.getBoolean(R.styleable.SwitchButton_isEnableThumbShadow, true)
        isOpenedLast = !isOpened
        array.recycle()
    }


    /**
     * 设置按钮初始状态
     */
    private fun initButtonState() {
        if (isOpened) {
            bgAlpha = 255
            animatedFraction = 0f
            thumbOffsetParent = 1.0f
        } else {
            bgAlpha = 0
            animatedFraction = 1f
            thumbOffsetParent = 0f
        }
    }


    /**
     * 测量控件尺寸
     * @param widthMeasureSpec Int
     * @param heightMeasureSpec Int
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultWidth = trackWidth
        var defaultHeight = calculateHeight()
        val width = measureSize(widthMeasureSpec, defaultWidth)
        val height = measureSize(heightMeasureSpec, defaultHeight)
        setMeasuredDimension(width, height)
    }


    /**
     * 计算控件最大高度
     * @return Int
     */
    private fun calculateHeight(): Int {
        var height = if (thumbSize > trackHeight) {
            thumbSize
        } else if (thumbSize + thumbShadowSize > trackHeight) {
            thumbSize
        } else {
            trackHeight
        }
        if (isEnableThumbShadow && height < thumbSize + thumbShadowSize) {
            height = +thumbShadowSize
        }
        return height
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initPath()
    }


    /**
     * 测量尺寸
     *
     * @param measureSpec
     * @param defaultSize
     * @return
     */
    private fun measureSize(measureSpec: Int, defaultSize: Int): Int {
        var result: Int
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        if (mode == MeasureSpec.EXACTLY) {
            result = size
        } else {
            result = defaultSize
            if (mode == MeasureSpec.AT_MOST) result = result.coerceAtMost(size)
        }
        return result
    }


    /**
     * 初始化画笔
     */
    protected open fun initPaint() {
        initTrackPaint()
        initThumbPaint()
    }

    /**
     * 初始化背景画笔
     */
    protected open fun initTrackPaint() {
        trackOnPaint.style = Paint.Style.FILL
        trackOnPaint.strokeJoin = Paint.Join.ROUND
        trackOnPaint.strokeCap = Paint.Cap.ROUND
        trackOnPaint.color = trackOnBgColor
        trackOnPaint.isAntiAlias = true
        trackOnPaint.isDither = true

        trackOffPaint.style = Paint.Style.FILL
        trackOffPaint.strokeJoin = Paint.Join.ROUND
        trackOffPaint.strokeCap = Paint.Cap.ROUND
        trackOffPaint.color = trackOffBgColor
        trackOffPaint.isAntiAlias = true
        trackOffPaint.isDither = true
    }


    /**
     * 初始化开关按钮画笔
     */
    private fun initThumbPaint() {
        thumbOnBgPaint.isAntiAlias = true
        thumbOnBgPaint.isDither = true
        thumbOnBgPaint.style = Paint.Style.FILL
        thumbOnBgPaint.strokeJoin = Paint.Join.ROUND
        thumbOnBgPaint.strokeCap = Paint.Cap.ROUND
        thumbOnBgPaint.color = thumbOnBgColor

        thumbOffBgPaint.isAntiAlias = true
        thumbOffBgPaint.isDither = true
        thumbOffBgPaint.style = Paint.Style.FILL
        thumbOffBgPaint.strokeJoin = Paint.Join.ROUND
        thumbOffBgPaint.strokeCap = Paint.Cap.ROUND
        thumbOffBgPaint.color = thumbOffBgColor

        thumbShadowPaint.isAntiAlias = true
        thumbShadowPaint.isDither = true
        thumbShadowPaint.style = Paint.Style.FILL
        thumbShadowPaint.strokeJoin = Paint.Join.ROUND
        thumbShadowPaint.strokeCap = Paint.Cap.ROUND
    }


    /**
     * 初始化View绘制路径
     */
    private fun initPath() {
        initTrackPath()
        initThumbConfig()
    }

    /**
     * 初始化背景绘制路径
     */
    protected open fun initTrackPath() {
        trackPath.reset()
        trackRectF.left = (width - trackWidth.toFloat()) / 2
        trackRectF.right = this.trackRectF.left + trackWidth
        trackRectF.top = (height - trackHeight.toFloat()) / 2
        trackRectF.bottom = this.trackRectF.top + trackHeight
        trackPath.addRoundRect(
            this.trackRectF,
            trackBgRadius.toFloat(),
            trackBgRadius.toFloat(),
            Path.Direction.CW
        )

    }

    /**
     * 初始化滑块默认路径
     */
    protected open fun initThumbConfig() {
        var padding = (height - thumbSize) * 0.5f
        if (isEnableThumbShadow) {
            padding -= (thumbShadowSize / 2)
        }
        thumbOffCenterX = padding + thumbSize * 0.5f
        thumbOnCenterX = width - padding - thumbSize * 0.5f
        thumbCenterY = height * 0.5f
        thumbTotalOffset = width - padding - thumbOffCenterX - thumbSize * 0.5f
        thumbShadowSize = (thumbSize - thumbShadowRadius * 3)
    }


    /**
     * 切换开关状态
     * @param isOpen Boolean
     */
    open fun toggleSwitch(isOpen: Boolean): Boolean {
        if (this.isOpened == isOpen) {
            return false
        }
        this.isOpenedLast = isOpened
        this.isOpened = isOpen
        startThumbAnimator()
        return true
    }


    /**
     * 执行滑动动画
     */
    protected open fun startThumbAnimator() {
        if (!isOpenedLast) {
            //off->on
            thumbAnimator.setFloatValues(0f, 1f)
        } else {
            //on->off
            thumbAnimator.setFloatValues(1f, 0f)
        }
        thumbAnimator.start()
    }


    /**
     * 绘制控件
     * @param canvas Canvas
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawFilter = PaintFlagsDrawFilter(0, ANTI_ALIAS_FLAG or FILTER_BITMAP_FLAG)
        onDrawToggleTrack(canvas)
        onDrawToggleThumbShadow(canvas)
        onDrawToggleThumb(canvas)
    }


    /**
     * 绘制开关按钮背景
     * @param canvas Canvas
     */
    protected open fun onDrawToggleTrack(canvas: Canvas) {
        canvas.save()
        canvas.drawPath(trackPath, trackOffPaint)
        trackOnPaint.alpha = bgAlpha
        canvas.drawPath(trackPath, trackOnPaint)
        canvas.restore()
    }


    /**
     * 绘制开关按钮
     * @param canvas Canvas
     */
    protected open fun onDrawToggleThumb(canvas: Canvas) {
        thumbPath.reset()
        var centerX = thumbOffCenterX + thumbOffsetParent * thumbTotalOffset
        thumbPath.addCircle(centerX, thumbCenterY, thumbSize * 0.5f, Path.Direction.CW)

        canvas.save()
        canvas.drawPath(thumbPath, thumbOffBgPaint)
        thumbOnBgPaint.alpha = bgAlpha
        canvas.drawPath(thumbPath, thumbOnBgPaint)
        canvas.restore()
    }

    /**
     * 绘制开关按钮阴影
     * @param canvas Canvas
     */
    protected open fun onDrawToggleThumbShadow(canvas: Canvas) {
        if (!isEnableThumbShadow) {
            return
        }
        thumbShadowPath.reset()
        var alpha = animatedFraction * 255
        var showDx = if (isOpenedLast) {
            thumbShadowDx.toFloat()
        } else {
            -thumbShadowDx.toFloat()
        }
        var centerX: Float = thumbOffCenterX + thumbOffsetParent * thumbTotalOffset
        thumbShadowPaint.alpha = alpha.toInt()
        thumbShadowPaint.setShadowLayer(
            thumbShadowRadius.toFloat(),
            showDx,
            thumbShadowDy.toFloat(),
            thumbBgShadowColor
        )
        thumbShadowPath.addCircle(centerX, thumbCenterY, thumbShadowSize * 0.5f, Path.Direction.CW)
        canvas.save()
        canvas.drawPath(thumbShadowPath, thumbShadowPaint)
        canvas.restore()
    }


    /**
     * 监听TouchEvent事件，处理按钮点击状态切换
     * @param event MotionEvent
     * @return Boolean
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //如果还在做动画，则不允许点击
        if (thumbOffsetParent != 0f && thumbOffsetParent != 1f) {
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (toggleSwitch(!isOpened)) {
                    toggleListener?.onSwitchToggleChange(isOpened, this)
                    callOnClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 开光状态监听
     * @param listener OnSwitchToggleChangeListener
     */
    fun setToggleChangeListener(listener: OnSwitchChangeListener) {
        this.toggleListener = listener
    }

}