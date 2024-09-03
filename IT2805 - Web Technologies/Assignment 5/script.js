/* Part 2 */
console.log('PART 2')
for (let i = 1; i < 21; i++){ //Rund a loop 20 times
    console.log(i) //Prints the number i for each loop
}

/* Part 3 */
console.log('PART 3')
const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]

for (i in numbers) { //Runs the loop once for each number in the array
    if (numbers[i]%3 === 0 && numbers[i]%5 === 0) { //Checks if the number is divisible in both 3 and 5
        console.log('eplekake')
    }
    else if (numbers[i]%3 === 0) { //Checks if the number is divisable by 3
        console.log('eple')
    }
    else if (numbers[i]%5 === 0) { //Checks if the number is divisible by 5
        console.log('kake')
    }
    else console.log(numbers[i]) //Logs to console if none of the criteria are met
}

/* Part 4 */
document.getElementById('title').innerText = 'Hello, JavaScript'; //changes the text of the element with the id 'title'

/* Part 5 */
function changeDisplay () {
    document.getElementById('magic').style.display = 'none'; //gets the element #magic and sets it style attribute 'display' to none.
}

function changeVisibility () {
    document.getElementById('magic').style.visibility = 'hidden';
    document.getElementById('magic').style.display = 'block';
}

function reset () {
    document.getElementById('magic').style.visibility = 'visible';
    document.getElementById('magic').style.display = 'block';
}

/* Part 6 */
const technologies = [
    'HTML5',
    'CSS3',
    'JavaScript',
    'Python',
    'Java',
    'AJAX',
    'JSON',
    'React',
    'Angular',
    'Bootstrap',
    'Node.js'
];

for (i in technologies) { //Loops once for each element of the array
    const ul = document.getElementById('tech'); //creates a constant called ul in the element with the id 'tech'
    const li = document.createElement('li'); //creates a constant called li creating a li element 

    li.appendChild(document.createTextNode(technologies[i])); //Adds the text of the chosen text from our array to the list
    ul.appendChild(li) //Adds said list to the unordered list with the id 'tech'
}