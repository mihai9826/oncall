let theValues;
if(sessionStorage.getItem('servingValues') == null){
	theValues = [];
	document.getElementsByName('servings').forEach(function(el, i){
		theValues[i] = el.value;
	});
	sessionStorage.setItem('servingValues', JSON.stringify(theValues));
} 

	theValues = JSON.parse(sessionStorage.getItem('servingValues'));
	document.getElementsByName('servings').forEach(function(el, i){
		if(theValues[i] === undefined)
			el.value = '1';
		else
			el.value = theValues[i];
	});

	document.getElementsByTagName('button')[0].addEventListener('click', function(){
		document.getElementsByName('servings').forEach(function(el, i){
			theValues[i] = el.value;
		});
		sessionStorage.setItem('servingValues', JSON.stringify(theValues));
	});
	
	
	document.getElementById('sendButton').addEventListener('click', function(e){
		for(let i in theValues) {
			theValues[i] = '1';
		}

		sessionStorage.setItem('servingValues', JSON.stringify(theValues));
	});

