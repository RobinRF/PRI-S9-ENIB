// Display.js

class HtmlDisplay {

	// ListView and contentView are DOMElement
	constructor(listView, contentView) {
		this.listView = listView;
		this.contentView = contentView;
	}

	update() {
		// Clear
		var poster = null;
		this.listView.innerHTML = '';
		this.contentView.innerHTML = '';

		// Test nimbus
		for (var i = 0; i < posterList.length; i++) {
			poster = posterList[i];
			if(poster.nimbus.isVisible()) {
				var item = document.createElement('li');
				item.innerHTML = poster.getTitle();
				this.listView.appendChild(item);

				// Test focus
				if (visitorFocus.isVisible(poster)) {
					this.contentView.innerHTML = poster.getDescription();
				}

			}
		}
	}

}