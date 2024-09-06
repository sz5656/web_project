/**
 * DOM 연습.
 */
console.log("basic.js")

let myName = 'Hong';
let myAge = 20;

const member = {
  id: 'user01',
  pw: '1111',
  email: 'user01@email.com',
  hobbies: ['sleeping','eating'],
  friends: [
    {name: 'Kim', phone: '1234-1234'},
    {name: 'Kang', phone: '2345-2345'},
    {name: 'Choi', phone: '2323-1414'}
  ],
  showHobby: function() {
    let result = '';
    this.hobbies.forEach(item => {
      result += item + ' ';
    });
    return result;
  }
}

member.phone = '010-1111-2222';
member.name = 'Hong';
console.log(member.id);
console.log(member['pw']);
let mailAddress = 'email';
console.log(member[mailAddress]);

console.log(member.hobbies[1]);
console.log(member.friends[1].name);
console.log(member);
// DOM.
let tag = document.createElement('p');
tag.innerText = '이름: ' + member.name;
document.querySelector('#show').appendChild(tag);

tag = document.createElement('p');
tag.innerHTML = '취미: <b>' + member.showHobby() + '</b>';
document.querySelector('#show').appendChild(tag);
tag = document.createElement('ul');
for(let i = 0; i < member.friends.length; i++) {
  let li = document.createElement('li');
  li.innerHTML = member.friends[i].name + ' ' + member.friends[i].phone;
  let btn = document.createElement('button');
  btn.innerHTML = '삭제';
  btn.addEventListener('click',function() {
    li.remove();
  })
  li.appendChild(btn);
  tag.appendChild(li);
}
member.friends.forEach(friend => {
  let li = document.createElement('li');
  li.innerHTML = friend.name + ' ' + friend.phone;
  let btn = document.createElement('button');
  btn.innerHTML = '삭제';
  btn.addEventListener('click',function() {
    li.remove();
  })
  li.appendChild(btn);
  tag.appendChild(li);
})

document.querySelector('#show').appendChild(tag);