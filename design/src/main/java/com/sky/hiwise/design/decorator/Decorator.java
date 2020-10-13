package com.sky.hiwise.design.decorator;

abstract class Decorator extends Component {

	 protected Component component;

     public void SetComponent(Component component)
     {
         this.component = component;
     }

     public void Operation()
     {
         if (component != null)
         {
             component.Operation();
         }
     }


}
