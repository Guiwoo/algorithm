const method09 = () => {
  /** 배열 */
  const ArrayMethodChecking = () => {
    var a = ["a", "b", "c"];
    var b = ["x", "y", "z"];
    // concat 매서드는 자신의 복사본에 넘어온인수를 합쳐 새로운 배열반환
    var c = a.concat(b, true);
    // console.log(c);

    a.push("d");
    var c = a.join("");
    // join 은 배열을 문자열로 반환, 많은수의 문자열을 반환해야한다면 ,배열에 담은후 join 하면 빠름
    // console.log(c);

    var a = ["a", "b", "c"];
    var c = a.pop();
    // pop 과 push 는 배열을 마치 스택과 같이 이용할수 있음 좋은데 ?

    var d = a.push("holy");
    // push 는 concat 과다르게 배열 자체를 변환
    // console.log(c, a);

    var a = ["a", "b", "c"];
    var b = a.reverse();
    // reverse 는 배열 의 모든 요소를 역순으로 정렬하여배열 반환

    var a = ["a", "b", "c"];
    var c = a.shift();
    // shift 는 배열 첫번쨰 요소를 제거 (큐같네) pop 보다 느림

    var a = ["a", "b", "c"];
    a.slice(1, 2);
    // 배열을 복사함 end를 지정하지 않을경우 디폴트는 배열의 길이가됨,
    // 문자열 slice 와 배열 slice 는 다르다

    var a = [9, 12, 14, 1, 6, 33, 97, 5, 8];
    // a.sort();
    //왜정렬을 똑바로 못하냐 ? 숫자를 문자로 인식해서 생긴오류 ascii 코드로 바꾸고 정렬 후에 다시 변환 ?
    a.sort((a, b) => a - b);
    // console.log(a);

    ("문자열 수열 섞인 배열");
    var m = ["aa", "bb", "a", 4, 8, 15, 16, 23, 42];
    m.sort((a, b) => {
      if (a === b) {
        return 0;
      }
      if (typeof a === typeof b) {
        return a < b ? -1 : 1;
      }
      return typeof a < typeof b ? -1 : 1;
    });
    // console.log(m);

    //객체를 정렬하고싶을떈 ?
    var s = [
      {first: "Joe", last: "Besser"},
      {first: "Moe", last: "Howard"},
      {first: "Shrimp", last: "Derita"},
      {first: "Poe", last: "Poter"},
      {first: "Larry", last: "Fine"},
      {first: "Currly", last: "Bower"},
    ];

    var by = (name) => {
      return function (o, p) {
        var a, b;
        if (typeof o === "object" && typeof p === "object" && o && p) {
          a = o[name];
          b = p[name];
          if (a === b) {
            return 0;
          }
          if (typeof a === typeof b) {
            return a < b ? -1 : 1;
          }
          return typeof a < typeof b ? -1 : 1;
        } else {
          throw {
            name: "error",
            message: "Expected an object when sorting by",
          };
        }
      };
    };

    s.sort(by("first")).sort(by("last"));
    // console.log(s);

    var by2 = (name, minor) => {
      return function (o, p) {
        var a, b;
        if (typeof o === "object" && typeof p === "object" && o && p) {
          a = o[name];
          b = p[name];
          if (a === b) {
            return typeof minor === "function" ? minor(o, p) : 0;
          }
          if (typeof a === typeof b) {
            return a < b ? -1 : 1;
          }
          return typeof a < typeof b ? -1 : 1;
        } else {
          throw {
            name: "error",
            message: "Expected an object when sorting by",
          };
        }
      };
    };
    // s.sort(by2("last", by("first")));

    var a = ["a", "b", "c"];
    var r = a.splice(1, 1, "ache", "bug");
    // Splice 는 기존배열요소를 제거하고 그위에 이친구들을 대입,slice 랑 혼동해서 사용하지 말자
    // console.log(a, r);

    var a = ["a", "b", "c"];
    var r = a.unshift("?", "@");
    // 배열의 새로운 길이를 반환하고 배열을 수정, 배열의 앞에다가 추가
    // console.log(r, a);
  };
  /** 함수 */
  var x = function () {
    return this.value;
  }.bind({value: "666"});

  /** 숫자 */
  const NumberToString = () => {
    //소수점 몇자리 까지 표시할지 정함 20자리 까지 가능함
    console.log(Math.PI.toExponential(7));
    //고정소수점 형태로 변환 20자리까지가능
    console.log(Math.PI.toFixed(20));
    //10진수 형태의 문자열로 변환 1~21, 반환되는 문자열에 포함된숫자의개수
    console.log(Math.PI.toPrecision(2));
    // 숫자를 문자열로 변환 합니다,toString(기본 10진법 바꾸기가능)
    console.log(Math.PI.toString(2));
    console.log(Math.PI.toString(16));
  };
  /** 객체 */
  const ObjectType = () => {
    //hasOwnProperty(name) 있으면 true 없으면 false
    var a = {member: true};
    var b = Object.create(a);
  };

  /** Regex 분리 */
  const RegexType = () => {
    var text =
      "<html><body bgcolor=linen><p>" +
      "This is <b>bold</b>!</p></body></html>";
    var tags = /[^<>]+|<(\/?)([A-Za-z]+)([^<>]*)>/g;
    //=> exec 가장 느리지만 가장 강력함

    // test 메서드는 가장간단하고 빠름, 있으면 true 없으면 false
    var b = /&.+;/.test("frank &amp; beans");
    console.log(b);
  };

  /** 문자열 */
  // .charAt 문자열의 포지션을 반환 합니다. 0보다 작거나 length 보다 크면 빈문자열 반환
  var name = "Guiwoo";
  var checkPoint = name.charAt(5); // o

  // .charCodeAt Ascii 숫자로 반환
  var name = "Guiwoo";
  var checkPoint = name.charCodeAt(5); // 111

  //.concat 이거잘안씀 + 쓰면 깔끔쓰하게 붙여쓸수 있음
  var s = "C".concat("a", "t"); // Cat
  var d = "C" + "at"; // Cat

  //indexOf(string,position) 첫번쨰 찾은 문자열의 위치를 반환,아니면 -1
  var text = "Mississippi";
  var p = text.indexOf("ss"); //p = 2
  p = text.indexOf("ss", 3); // p = 5
  p = text.indexOf("ss", 6); // -1

  //LastIndexOf(string,Position) 반대로 문자열의 위치반환 뒤에서부터
  var text = "Mississippi";
  var p = text.indexOf("ss"); //p = 5
  p = text.indexOf("ss", 3); // p = 2
  p = text.indexOf("ss", 6); // 5

  // .localeCompare(that) that 과 스트링 비교 문자열보다 적으면 - 1같으면 0 크면 양수
  var m = ["AAA", "A", "aa", "a", "Aa", "aaa"];
  m.sort(function (a, b) {
    return a.localeCompare(b);
  });

  // .match(regexp) 정규표현식 과 일치하는 부분을 문자열에서 찾음, g플레그에 따라 달라짐
  // -g 플래그 설정안되어 있으면 반환값 똑같음 regexp.exec(string) 이랑 g 설정되면 다름

  var url = "http://www.youtube.com";
  var regex = /(?:http):\/\/[?:a-z]+.[?:a-z]+.[?:a-z]+/g;

  // a = url.match(regex);
  // for (let i = 0; i < a.length; i++) {
  //   console.log(a[i]);
  // }

  // .replace(search,replace) 검색및 대체 작업을 실행 새로운 문자열을 생성

  var p =
    "The quick brown fox jumps over the lazy dog. If the dog reacted, was it really lazy?";

  // console.log(p.replaceAll("dog", "monkey")); // node 에는 replaceAll 을 지원안하는듯 ?

  // .search(regexp) indexof와 같지만 정규식을 받음 인자로 g플래그 무시됨
  var text = "and in it e says 'Any damn fool could'";
  var pos = text.search(/["']/g);
  // console.log(pos, text[pos], text[pos + 1]);

  // .slice(start,end) 문자열의 일부분을 복사해서 새로운 문자열을 만듭니다.
  var text = "and in it e says 'Any damn fool could'";
  var a = text.slice(17); //'Any damn fool could'
  var a = text.slice(0, 3); // and

  // .split(seperator,limit) 문자열을 쪼개어 각각의 항목이 담긴 배열을 생성합니다.
  var digit = "0123456789";
  var a = digit.split("", 5); //[ '0', '1', '2', '3', '4' ]
  var text = "last, first ,middle";
  var d = text.split(/\s*,\s*/); //[ 'last', 'first', 'middle' ]
  // regex 를 넘길떄는 조심 캡쳐그룹의 아이도 배열에 반환됨 빈문자열도 종종오고 조심해서 사용하자 2번 확인
};
