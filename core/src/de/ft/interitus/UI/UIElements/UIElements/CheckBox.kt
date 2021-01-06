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

class CheckBox : UIElement{

     var toggleState:Boolean
    var label:TextLabel = TextLabel()
    var disabled:Boolean = false
    var stateEvent: EventManager<UIToggleEvent> = EventManager();

    private  var xMove = 0
    private var yMove = 0;
    private var movement = 1;
    private var clickedOn:Boolean = false;
    private val labelMargin = 10
    private var InnerBoarderMargin = 3;
    private var size = 15;


    constructor(toggleState: Boolean, text: String) : super() {
        this.toggleState = toggleState

        label.setText(text);
    }



    override fun draw() {

        if(!disabled&&Gdx.input.isButtonPressed(0)&& CheckCollision.checkpointwithobject(x,y,size,size, Var.mouseDownPosWithoutUnproject.x,Gdx.graphics.height-Var.mouseDownPosWithoutUnproject.y)) {
        xMove = movement
        yMove = movement;
            clickedOn = true;
        }else{
            xMove = 0
            yMove = 0
        }

        if(clickedOn&&!disabled&&!Gdx.input.isButtonPressed(0)&&CheckCollision.checkpointwithobject(x,y,size,size,Var.mouseReleasePosWithoutUnproject.x,Gdx.graphics.height-Var.mouseReleasePosWithoutUnproject.y)) {
            clickedOn = false
            toggleState = !toggleState

            stateEvent.fireForEach {
                it.toggled(toggleState,this)

            }

        }


        WindowManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        WindowManager.shapeRenderer.setAutoShapeType(true)
        if(disabled) {
            WindowManager.shapeRenderer.color = UIManager.disabledColor

        }else{
            WindowManager.shapeRenderer.color = UIManager.primaryColor
        }
        WindowManager.shapeRenderer.rect(x.toFloat()+xMove,y.toFloat()-yMove,size.toFloat(),size.toFloat())

        if(toggleState) {
            WindowManager.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            WindowManager.shapeRenderer.rect(x.toFloat() + xMove+InnerBoarderMargin, y.toFloat() - yMove+InnerBoarderMargin, size.toFloat()-InnerBoarderMargin*2, size.toFloat()-InnerBoarderMargin*2)
        }
        WindowManager.shapeRenderer.end()

        this.h = size;
        this.w = size+labelMargin+label.getW()
        label.setPosition(this.x+this.size+this.labelMargin,this.y+this.size/2-label.getH()/2);
        label.draw()

    }


}
