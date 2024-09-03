let tasks = []

function addTask(input) {
    document.getElementById('todo').value = ''

    const task = {
        task: input,
        date: Date.now()
    }
    tasks.push(task)

    const ul = document.getElementById('list')
    const li = document.createElement('li')
    const box = document.createElement('input')
    box.setAttribute('type', 'checkbox')

    li.appendChild(box)
    li.appendChild(document.createTextNode(input))
    ul.appendChild(li)

    counter()
}
document.getElementById('form').addEventListener('submit', function(event) {
    event.preventDefault()
    let input = document.getElementById('todo').value

    if (input !== '') {
        addTask(input)  
    }
})
document.querySelector('ul').addEventListener('click', function(event) {
    if (event.target.tagName == 'INPUT') {
        event.target.parentNode.classList.toggle('done')
        counter()
    }
})
function counter() {
    let amount = document.getElementsByTagName('li').length
    let completed = document.getElementsByClassName('done').length

    document.getElementById('count').value = completed + '/' + amount
}