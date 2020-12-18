package com.soywiz.korge.view

import com.soywiz.korag.AG
import com.soywiz.korge.render.RenderContext
import com.soywiz.korma.geom.Size

class Pipeline(val views: Views , width : Int , height : Int , isActive : Boolean = true, rb : AG.BaseRenderBuffer? = null , st : Stage ? = null) {
    private var stage : Stage
    private var width = width
    private var height = height
    var renderBuffer : AG.BaseRenderBuffer ?
    var renderCompleteCallback : (() -> Int)? = null
    var isActive : Boolean = isActive
    val ag = views.ag

    init {
        renderBuffer = rb

        if (st != null) {
            this.stage = st
        } else {
            this.stage = Stage(views)
        }
    }

    fun render(renderContext : RenderContext) {
        if (!isActive) {
            return
        }

        if (renderBuffer == null) {
            stage.render(renderContext)
            return
        }

        renderContext.renderToCustomBuffer(
            width ,
            height ,
            render = {
                stage.render(renderContext)
                renderCompleteCallback?.invoke()
                isActive = false
            } ,
            rb = this.renderBuffer!!)
    }

    fun SetActive(isActive: Boolean) {
        this.isActive = isActive
    }

    fun SetWidth(width: Int) {
        this.width = width
    }

    fun SetHeight(height: Int) {
        this.height = height
    }

    fun update() {

    }
}
