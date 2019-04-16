## kotlin中的设计模式

### 1 创建型模式

提供创建对象的同时隐藏创建逻辑的方式，而不是直接实例化一个对象。这使得程序在创建实例时更加灵活。

#### 1.1 单例模式

单例模式是为了保证一个类仅有一个实例，并提供一个全局的访问方式。

主要实现是判断系统是否已经生成了实例，如果有则直接返回，如果没有则创建。

在kotlin中单例声明直接使用object关键字进行对象声明：

    object PrinterDriver{}
    
对象声明的初始化过程是线程安全的。如果需要引用该对象直接使用其名称即可。

#### 1.2 简单工厂模式

简单工厂模式是由单一的一个工厂对象决定创建出哪一种产品类的实例。但是很明显该工厂类集中了所有实例的创建逻辑，这违反了高内聚的责任分配原则。

#### 1.3 工厂方法模式

在工厂方法模式中，核心的工厂类不再负责所有产品的创建，而是将具体创建的工作交给子类去做。

该核心类成为一个抽象工厂角色，仅负责给出具体工厂子类必须实现的接口，而不接触哪一个产品类应当被实例化这种细节。

与简单工厂模式相比，简单工厂模式是一个单一的工厂类，而工厂方法模式下一个产品对应一个工厂。

#### 1.4 抽象工厂

抽象工厂主要的目的是围绕一个超级工厂创建其他的工厂。抽象工厂模式提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类。

可以考虑虚拟形象换皮肤的情况，有上衣、下装、背景等等，但是换的时候也可以一整套一整套的换。

与工厂方法模式相比，如果只有一个产品族，可以用工厂方法模式，只要有多于一个的产品族，就可以使用抽象工厂模式。

#### 1.5 Builder模式

使用多个简单的对象一步一步构造成一个复杂的对象，Builder模式在Android中经常见到，比如创建一个AlertDialog。

与工厂方法不同之处在于，工厂方法不关心创建过程，只关心由哪个工厂创建。但是Builder模式关心组装各个零配件而产生一个新的产品，并且还可以关注组装的顺序和细节。

#### 1.6 原型模式

原型模式是以一个对象为原型，创建出一个新的对象，在kotlin下很容易实现，直接使用data class。

因为data class会自动获得equals、hashCode、toString和copy方法，而copy方法可以克隆整个对象并且允许修改新对象的某些属性。

与直接实例化一个新对象不同的是，原型模式是通过克隆现有的对象产生新对象。

    data class Email(var recipient:String, var subject:String?, var message:String?)
    val mail=Email("abc@example.com", "Hello", "Don't know what to write.")
    val copy=mail.copy(recipient = "other@example.com")

### 2 结构型模式

#### 2.1 适配器模式

适配器模式是把一个不兼容的接口转化为另一个可以使用的接口。

    interface Target {
        fun request()
    }
    
    interface Adaptee {
        fun ask()
    }
    
    class Adapter(val wrapper: Adaptee) : Target {
        override fun request() {
            wrapper.ask()
        }
    }
    
适配器不是在详细设计时添加的，而是解决正在服役项目的问题。

#### 2.2 桥接模式

桥接模式可以分离在某个类中存在的两个独立变化的维度，把多层继承结构改为两个独立的继承结构。

在两个抽象层中有一个抽象关联。

#### 2.3 组合模式

组合模式用于把一组相似的对象当做一个单一的对象，组合模式依据树形结构来组合对象。

实际就是在一个对象中包含其他对象，实现一个树。把树的根节点当做一个单一对象。

不过在定义时，组合模式不是用的接口而是实现类，违反了依赖倒置的原则。

#### 2.4 装饰模式

给一个对象添加额外的行为，kotlin中可以直接使用扩展函数实现。

#### 2.5 外观模式

外观模式也叫门面模式，是为一个复杂的子系统提供一个简化的统一接口。

可以类比于一个医院，使用者不必知道里面的实现，只需要提供一个接待人员。

#### 2.6 享元模式

享元模式是以共享的方式高效的支持大量细粒度对象的重用。

主要需要区分可共享的内部状态和不可共享的外部状态。

比如围棋，如果为每个黑子和白字初始化一个实例，那么实例就太多了，所以可以通过享元模式来共用实例。

只为实例黑子和白子两个实例，具体的位置则处于变化，用这种方式实现可以大大减少实例的数量。

#### 2.7 过滤器模式

系统中使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式将其连接起来。

也可以结合多个过滤器合成一个过滤器。

#### 2.8 代理模式

代理模式是使用一个代理对象来访问目标对象的行为。一般用在直接访问目标对象容易出现问题的情况下。

和适配器模式的区别是适配器改变所考虑对象的接口，代理模式不改变接口。

和装饰器模式的区别是装饰器模式是为了增强功能，而代理模式是为了加以控制。

### 3 行为型模式

#### 3.1 职责链模式

职责链通过建立一条链来组织请求的处理者，请求沿着链进行传递，请求无需知道请求如何处理，实现请求者和处理者的解耦。

比如对log的显示，有error，warning，debug等多个等级，一层层的请求，由相应等级的日志显示logger输出。

再比如ViewGroup对触摸事件的处理，触摸事件一层层传递，最后由中间某层的View来处理。

#### 3.2 命令模式

将请求以一个一个命令的方式封装在对象中，并传给调用对象，对请求排队以及记录请求日志，以及支持可撤销的操作。

#### 3.3 解释器模式

解释器模式是定义一个语言的文法，并且建立一个解释器来解释该语言中的语句，比如SQL语言。

应用的场景比较少。

#### 3.4 迭代器模式

迭代器模式提供一种遍历聚合对象中的元素的一种方式，而又无需暴露该对象的内部表示。

Kotlin中定义iterator迭代器函数即可，或者自己定义一个迭代器，包含next()和hasNext()方法。

    class Sentence(val words: List<String>)
    operator fun Sentence.iterator(): Iterator<String> = words.iterator()

#### 3.5 中介者模式

中介者模式用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使耦合松散，且能够独立改变之间的交互。

主要解决的是多个类相互耦合形成了网状结构，中介者模式可以将网状结构分离为星型结构。

但是需要在职责清晰的时候使用，如果职责混乱，必定难以封装成中介者。

#### 3.6 备忘录模式

备忘录模式是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先的状态。

实际使用时为了符合迪米特原则，还需要一个管理备忘录的类，并且可以将原型模式和备忘录模式结合起来使用。

#### 3.7 观察者模式

观察者模式是一个对象状态发生变化后，可以立即通知已订阅的另一个对象。

Kotlin中有observable properties简化实现。

    interface TextChangedListener {
        fun onTextChanged(newText: String)
    }
    class TextView {
        var listener: TextChangedListener? = null
        val text:String by Delegates.observable("") { prop, old, new ->
            listener?.onTextChanged(new)
        }
    }
    
#### 3.8 状态模式

状态模式是将一个对象在不同状态下的不同行为封装在一个个状态类中，通过设置不同的状态可以让对象拥有不同的行为。

在行为受状态约束的情况下使用状态模式，状态最好不要太多。

#### 3.9 策略模式

策略模式用于算法的切换和扩展，分离算法的定义和实现。

Kotlin中可以使用高阶函数作为算法的抽象。

    class Customer(val name: String, val fee: Double, val discount: (Double)->Double) {
        fun pricePerMonth() = discount(fee)
    }
    val studentDiscount = {fee: Double -> fee/2}
    val noDiscount = {fee: Double -> fee}
    
    val student = Customer("Ned", 10.0, studentDiscount)
    
#### 3.10 模板方法模式

模板方法模式定义一个模板方法来定义算法框架，而某些具体步骤的实现在其子类中完成。

Kotlin中可以使用高阶函数可以避免继承的方式。

    class Task{
        fun play(initialize: ()->Unit, startPlay: ()->Unit, endPlay: ()->Unit){
            initialize()
            startPlay()
            endPlay()
        }
        
        fun initialize(){ //TODO }
        fun startPlay(){ //TODO }
        fun endPlay(){ //TODO }
    }
    
#### 3.11 访问者模式

访问者模式提供一个访问者类，改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。

被访问者里提供一个方法接收访问者，并将自身引用传入访问者。

    interface Employee{ fun accept(visitor: Visitor) }
    interface Visitor{
        fun visit(ge: GeneralEmployee)
        fun visit(me: ManagerEmployee)
    }
    class GemeralEmployee(val wage: Int):Employee{
        override fun accept(visitor:Visitor) = visitor.visit(this)
    }
    class ManagerEmployee(val wage: Int, val bonus: Int):Employee{
        override fun accept(visitor:Visitor) = visitor.visit(this)
    }
    class FADVisitor:Visitor{
        override fun visit(ge: GeneralEmployee){
            println("GeneralEmployee wage:${ge.wage}")
        }
        override fun visit(me: ManagerEmployee){
            println("ManagerEmployee wage:${me.wage + me.bonus}")
        }
    }
    
### 4 JavaEE模式

主要是一些关注表示层的设计模式。

#### 4.1 MVC模式

MVC 模式代表 Model-View-Controller（模型-视图-控制器） 模式。这种模式用于应用程序的分层开发。

#### 4.2 业务代表模式

它的做法是一个业务代表类提供对所有业务服务的查询和分配操作的模式。

它基本上是用来减少通信或对表示层代码中的业务层代码的远程查询功能。

#### 4.3 组合实体模式

本质是把多个实体组合成一个单一的实体。

#### 4.4 数据访问对象模式

数据访问对象就是DAO，也就是把低级的数据访问 API 或操作从高级的业务服务中分离出来。

#### 4.5 前端控制器模式

是用来提供一个集中的请求处理机制，所有的请求都将由一个单一的处理程序处理。

该处理程序可以做认证/授权/记录日志，或者跟踪请求，然后把请求传给相应的处理程序。

#### 4.6 拦截过滤器模式

用于对应用程序的请求或响应做一些预处理/后处理。定义过滤器，并在把请求传给实际目标应用程序之前应用在请求上。

过滤器可以做认证/授权/记录日志，或者跟踪请求，然后把请求传给相应的处理程序。

#### 4.7 服务定位器模式

利用缓存，加快服务定位的模式。如果第一次请求服务，则去查找服务并缓存该服务对象。再次请求时直接从缓存中查找。

#### 4.8 传输对象模式

实际上就是传输POJO对象的方式。