/**
 * @constructor
 * @param [String}
 *            tetrisId
 */
function RestTetrisModel(tetrisId) {
	// Call the parent constructor
	TetrisModel.call(this);
	this.tetrisId = tetrisId;
	this.lastEventId = 0;
	this.handlers = new Array();
	var model = this;
	this.timer = $.timer(function() {
		model.getEvent();
	}, refreshTime, false);
};

RestTetrisModel.prototype.playNewTetris = function() {
	var model = this;
	jQuery(function($) {
		$.ajax({
			type : "POST",
			async : false,
			url : './ws/playing',
			contentType : 'application/json',
			data : '',
			success : function(data, textStatus, jqXHR) {
				model.tetrisId = data;
				model.getEvent();
			},
			dataType : 'text'
		});
	});
	return this.tetrisId;
};

RestTetrisModel.prototype.start = function() {
	var model = this;
	$.get('./ws/playing/' + model.tetrisId + '/start').done(
			function(data, textStatus, jqXHR) {
				console.log("start game for tetris id=" + model.tetrisId);
			}).fail(function(jqXHR, textStatus, errorThrown) {
		alert('error');
	});
};

RestTetrisModel.prototype.getEvent = function() {
	var model = this;
	jQuery(function($) {
		$.get(
				'./ws/playing/' + model.tetrisId + "/events?lastEventId="
						+ model.lastEventId).done(
				function(data, textStatus, jqXHR) {
					var events = data.tetrisEvent;
					// console.log("getEvents done : count=");
					for ( var i = 0; i < events.length; i++) {
						var event = events[i];
						for ( var j = 0; j < model.handlers.length; j++) {
							var handler = model.handlers[j];
							handler(event);
						}
						model.lastEventId = event.lastEventId;
					}
					model.timer.once(refreshTime);
				}).fail(function(jqXHR, textStatus, errorThrown) {
			model.timer.stop();
			alert('Error = ' + errorThrown);
		});
	});
};

RestTetrisModel.prototype.addTetrisEventHandler = function(handler) {
	this.handlers.push(handler);
	this.timer.once(refreshTime);
};

RestTetrisModel.prototype.rotatePiece = function(direction) {
	var model = this;
	jQuery(function($) {
		$.ajax({
			type : "POST",
			url : './ws/playing/' + model.tetrisId + '/rotate',
			contentType : 'application/json',
			data : '{ "rotate" : { "direction": ' + direction + ' }}',
			success : function() {
				console.log("rotate piece to " + direction + " for tetris id="
						+ model.tetrisId);
			}
		});
	});
	return this;
};

RestTetrisModel.prototype.movePiece = function(direction) {
	var model = this;
	jQuery(function($) {
		$.ajax({
			type : "POST",
			url : './ws/playing/' + model.tetrisId + '/move',
			contentType : 'application/json',
			data : '{ "move" : { "direction": ' + direction + '}}',
			success : function() {
				console.log("move piece to " + direction + " for tetris id="
						+ model.tetrisId);
			}
		});
	});
	return this;
};

RestTetrisModel.prototype.dropPiece = function() {
	var model = this;
	jQuery(function($) {
		$.ajax({
			type : "POST",
			url : './ws/playing/' + model.tetrisId + '/drop',
			contentType : 'application/json',
			success : function() {
				console.log("drop piece for tetris id=" + model.tetrisId);
			}
		});
	});
	return this;
};

RestTetrisModel.prototype.getBoard = function() {
	var model = this;
	var board = null;
	jQuery(function($) {
		$.ajax({
			type : "GET",
			async : false,
			url : './ws/playing/' + model.tetrisId + "/board",
			contentType : 'application/json',
			success : function(data, textStatus, jqXHR) {
				board = data.Result;
			}
		});
	});
	return board;
};