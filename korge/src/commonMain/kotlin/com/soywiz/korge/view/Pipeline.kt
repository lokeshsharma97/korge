package com.soywiz.korge.view

import com.soywiz.kds.BitSet
import com.soywiz.korag.AG
import com.soywiz.korge.render.RenderContext

class Pipeline(val views: Views , width : Int , height : Int , rb : AG.BaseRenderBuffer? = null) {
    var stage : Stage = Stage(views)
    var width = width
    var height = height
    var renderBuffer : AG.BaseRenderBuffer
    var renderCompleteCallback : (() -> Int)? = null
    var isActive : Boolean = false
    val ag = views.ag

    init {
        if (rb != null) {
            renderBuffer = rb
        } else {
            renderBuffer = views.ag.renderBuffers.alloc()
        }
    }

    fun render(renderContext : RenderContext) {
        if (!isActive) {
            return
        }

        ag.renderToExternalRB(
            width ,
            height ,
            render = {
                stage.render(renderContext)
                renderCompleteCallback?.invoke()
                isActive = false
            } ,
            rb = this.renderBuffer)
    }

    fun update() {

    }
}
