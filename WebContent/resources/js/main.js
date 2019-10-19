
checkCookButton();


	let theScroll;
	if(sessionStorage.getItem('scrollPos') == null){
	    theScroll = [];
	    sessionStorage.setItem('scrollPos', JSON.stringify(theScroll));

	} else {
	    theScroll = JSON.parse(sessionStorage.getItem('scrollPos'));
	    window.scrollTo(0,theScroll[theScroll.length - 1]);
	}



function checkCookButton(){
	
	const theLink = document.getElementById('cookLink');
	
	if(theLink === null){
		if(document.getElementById('loginButton') === null)
			document.querySelectorAll('.fourty')[1].remove();
	} else{
		theLink.className += 'ui black button';
	    document.querySelectorAll('.fourty')[1].appendChild(theLink);
	}
		
}

document.getElementById('nav').addEventListener('click', function(e){
    if(document.querySelector('.active') !== null)
        document.querySelector('.active').classList.remove('active');
    if(e.target.classList.contains('header') === false)
        e.target.className += ' active';
});

window.addEventListener('scroll', function(){
    theScroll.push(window.pageYOffset);
    sessionStorage.setItem('scrollPos', JSON.stringify(theScroll));
});










