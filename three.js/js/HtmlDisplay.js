// Display.js

class HtmlDisplay {

	// ListView and contentView are DOMElement
	constructor(listView, contentView) {
		this.listView = listView;
		this.contentView = contentView;
	}

	update() {
		// Clear
		var focuscone=camera.getObjectByName("FocusCone")
		var poster = null;
		this.listView.innerHTML = '';
		this.contentView.innerHTML = '';
		var distance = 300;
		// Test nimbus
		for (var i = 0; i < focuscone.target.length; i++) {
			poster = focuscone.target[i];
			//console.log(poster)
			if(poster.nimbus.isVisible()) {
				var item = document.createElement('li');
				item.innerHTML = poster.name;
				this.listView.appendChild(item);

				// Test focus
				if (focuscone.canSee(poster)&&poster.nimbus.getDistanceToObserver()<distance) {
					this.contentView.innerHTML = poster.description;
					distance=poster.nimbus.getDistanceToObserver()
				}

			}
		}
	}

}