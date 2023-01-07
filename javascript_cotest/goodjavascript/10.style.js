/**
 나쁜 자바스크립트 코드작성 습관
 1. 타입강제
    - 자바스크립트는 타입을 무시하는경향이 있다.
    - ex) 2 + '2', [] + 2
    여기서 는 적용이 안되는듯 ?
 */
const corecion = () => {
  let a;

  if (a == 1) {
    console.log("Adding 10 to ", a);
    a += 10;
    console.log("a : ", a);
  }

  if (a > 2) {
    console.log("Foring a to be 1 in a strange way", a);
    a = a - (a - 1);
    console.log("a :", a);
  }
  let b = 100;

  let stringA = "" + b;

  let stringB = b.toString();

  let numberA = +stringA;
  let numberB = Number(b);

  console.log(stringA, stringB, numberA, numberB);
};

/**
 2. 지금 var 를 써 ????
    - 이득 ? 2개의 스코프 함수 그리고 글로벌
    - 어디서든 선언하든 아무대서 쓸수있고 
    - 이미 있어도 다시 선언할수있고 불평도안해

    let 을 대신 쓰자 블록안에 서만 접근가능
 */

/**
 3. 일반함수 와 화살표함수의 차이점을 이해못하고 사용
    - this
        - 일반함수는 this 라는 객체가 추가됨
        - => 에는 없음 단지 상위 단계의 this 를 가르킴(call,apply,bind 못씀)

    - 생성자 함수로 사용가능 여부
        - 화살표 함수는 프로퍼티를 가지고 있지않음
    
    - arguments 사용가능 여부
        - 인자에 추가하지않아도 일반함수는 받아올수있음
        - 화살표는 안됨 (rest 로 해결가능 이지)
    
    - 화살표함수는 중복된 인자를 허락하지않음
        - 일반함수에서는 되지만 "non-strict mode"

 */

/**
 4. This 를 이해하지못함.
    - 자바스크립트는 하나의 객체이다, 함수 는 객체,매서드도 객체
    - this 는 아무곳에서 사용가능하다, 함수안에서도.
    - 글로벌스코프 밖에서도 사용가능하고, 흥미로운 값을 얻을수 있다.
    - this 키워드는 현재 실행 컨텍스트를 언급한다.
     - 함수안에서 써? 그러면 함수가 현재 실행 컨텍스트다.
    - 화살표함수 안에서 써? 
        - 함수의부모가 실행컨텍스트 이다.
    - 매서드 안에서써?
        - 모든 프로퍼티 정의 객체 클래스의 한부분을 포함한다.
 */
const whatIsThis = () => {
  const thisEx = () => {
    const test = {
      prop: 42,
      func: function () {
        return this.prop; // 위에있는 값을 참조 상위블록
      },
    };
    console.log(test.func());
  };

  // console.log(this === window); 웹에서는 winodw 객체가 전역
  // a = 37;
  // console.log(window.a);

  const howToCallThis = () => {
    function f1() {
      return this;
    }
    console.log(f1() === global); // node 에서는 True ,window 에서는 flase
    //But Stirct 모드는 this 하면 undefined 나옴
  };

  //This 를 다음 문맥으로 넘기는 방법
  const sendThisToNext = () => {
    var obj = {a: "Custom"};
    var a = "Global";

    function whatsThis() {
      return this.a;
    }
    whatsThis(); // undefined
    whatsThis.call(obj); // Custom
    whatsThis.apply(obj); // Custom

    function add(c, d) {
      return this.a + this.b + c + d;
    }
    var o = {a: 1, b: 3};
    add.call(o, 5, 7); // 16 콜은 그냥 흩뿌려
    add.apply(o, [5, 7]); //16 배열을 보내주네 ? apply 는

    function bar() {
      console.log(Object.prototype.toString.call(this));
    }
    bar.call(7); //[object Number]
    bar.call("foo"); //[object string]
    bar.call([0, 1]); // [object Array]
    bar.call(undefined); //[object global]
  };

  //Bind 메서드
  const howToUseBind = () => {
    function f() {
      return this.a;
    }
    var g = f.bind({a: "HOLY"});
    console.log(g()); //Holy

    var h = g.bind({a: "WAK"}); // bind는 한번만작동하네 ?
    console.log(h());

    var i = {a: 37, f: f, g: g, h: h};
    console.log(i.a, i.f(), i.g(), i.h()); //37 37 HOLY HOLY
  };

  //화살표함수
  const arrowFunction = () => {
    var globalObject = this;
    var foo = () => this;
    console.log(foo() === globalObject);

    var obj = {func: foo};
    console.log(obj.func() === globalObject);
    console.log(foo.call(obj) === globalObject);

    foo = foo.bind(obj);
    console.log(foo() === globalObject);
    var obj = {
      bar: function () {
        var x = () => this;
        return x;
      },
    };

    var fn = obj.bar();
    console.log(fn() === obj);
  };

  //객체의 메서드로서
  const methodOfObject = () => {
    var o = {
      prop: 37,
      f: function () {
        return this.prop;
      },
    };
    console.log(o.f());

    var o2 = {prop: 37};
    function independent() {
      return this.prop;
    }
    o2.f = independent;
    console.log(o2.f());

    o.b = {g: independent, prop: 42}; // 즉각적인 참조가 제일중요함
    console.log(o.b.g());

    var o = {
      f: function () {
        return this.a + this.b;
      },
    };
    var p = Object.create(o);
    p.a = 1;
    p.b = 4;

    console.log(p.f());
  };

  // 접근자와 설정자의 this
  // 접근자 설정자 호출해도 동일

  const getterOfObject = () => {
    function sum() {
      return this.a + this.b + this.c;
    }
    var o = {
      a: 1,
      b: 2,
      c: 3,
      get average() {
        return (this.a + this.b + this.c) / 3;
      },
    };
    Object.defineProperty(o, "sum", {
      get: sum,
      enumerable: true,
      configurable: true,
    });
    console.log(o.average, o.sum);
  };

  //생성자로써 의 접근
  const newFunction = () => {
    function C() {
      this.a = 37;
    }
    var o = new C();
    console.log(o.a);

    function C2() {
      this.a = 37;
      return {a: 38};
    }
    o = new C2();
    console.log(o.a);
  };
};
// Useful ex
const fukcingCool = () => {
  class Person {
    constructor(first_name, last_name) {
      (this.f_name = first_name), (this.l_name = last_name);
    }
  }

  function greetPerson() {
    console.log("Helloe there", this.f_name, this.l_name);
  }

  let oPerson1 = new Person("Guiwoo", "Park");
  let oPerson2 = new Person("Daehee", "GO");

  greetPerson.call(oPerson1);
  greetPerson.call(oPerson2);
};
/**
 5. 잘못된 클로저 사용으로 인한 메모리 누수
 */

function X() {
  let bigVariable = Array(100000).join("*");

  let Xstate = globalState().get("X");

  let fn1 = function () {
    if (!Xstate) {
      console.log("Not stored");
    }
  };

  globalState().set("X", {
    state: bigVariable,
    logic: () => {
      console.log("This is my method");
    },
  });
}
