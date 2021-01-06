/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */
package de.ft.interitus.UI.UIElements.UIElements

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import de.ft.interitus.UI.UIElements.UIElements.labels.TextLabel
import de.ft.interitus.UI.UIElements.check.CheckCollision
import de.ft.interitus.UI.uiManagement.UIManager
import de.ft.interitus.Var
import de.ft.interitus.WindowManager
import de.ft.interitus.events.UI.EventManager
import de.ft.interitus.events.UI.UIToggleEvent

class RadioButton : UIElement {

    private var toggleState: Boolean
    private var pressed: Boolean = false
    private var radius = 7f
    private val segments = 40
    private var text = ""
    private val label = TextLabel(text)
    private val labelMargin = 10
    private  var xMove = 0
    private var yMove = 0;
    private var movement = 1;
    val toggleEvent = EventManager<UIToggleEvent>()

    constructor() {
        this.toggleState = false
        init()
    }

    constructor(text: String) {
        this.text = text
        this.toggleState = false
        init()
    }

    constructor(toggleState: Boolean) {
        this.toggleState = toggleState
        init()
    }

    constructor(text: String, toggleState: Boolean) {
        this.toggleState = toggleState
        this.text = text
        init()
    }

    private fun init() {
        label.setText(this.text)

    }

    override fun draw() {


        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        WindowManager.shapeRenderer.setAutoShapeType(true)

        if (!disabled&&Gdx.input.isButtonPressed(0)&&CheckCollision.checkCircleWithVector(radius.toInt(), this.x, this.y, Var.mouseDownPosWithoutUnproject)) {
           xMove = movement;
            yMove = movement;

        }else{
            xMove = 0
            yMove = 0
        }

        Gdx.gl.glLineWidth(2F)
        if (!super.disabled) {
            WindowManager.shapeRenderer.color = UIManager.primaryColor
        } else {
            WindowManager.shapeRenderer.color = UIManager.disabledColor
        }



        WindowManager.shapeRenderer.circle(this.x.toFloat()+xMove, this.y.toFloat()-yMove, radius, segments)
        WindowManager.shapeRenderer.set(ShapeRenderer.ShapeType.Filled)



        if (toggleState) {
            WindowManager.shapeRenderer.circle(this.x.toFloat()+xMove, this.y.toFloat()-yMove, radius - 3, segments)
        }
        if (!this.disabled) {
            if (Gdx.input.isButtonPressed(0)) {
                pressed = true
            }

            if (pressed && !Gdx.input.isButtonPressed(0)) {
                pressed = false
                if (CheckCollision.checkCircleWithVector(radius.toInt(), this.x, this.y, Var.mouseReleasePosWithoutUnproject)) {
                    toggleState = !toggleState
                    toggleEvent.fireForEach {
                        it.toggled(toggleState,this)

                    }
                }

            }
        }

        WindowManager.shapeRenderer.end()

        if (label.getX() != (this.x + this.radius / 2 + this.labelMargin).toInt() || label.getY() != (this.y - this.radius / 2).toInt()) {
            label.setPosition((this.x + this.radius / 2 + this.labelMargin).toInt(), (this.y - this.radius / 2).toInt())
        }

        label.draw()

        this.w = (this.label.w + this.radius + this.labelMargin).toInt()
        this.h = this.radius.toInt()


    }

    fun setToggleState(state: Boolean) {
        this.toggleState = state
    }

    fun getToggleState(): Boolean {
        return this.toggleState
    }


}
