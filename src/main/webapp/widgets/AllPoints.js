/* AllPoints */
define([
    'dojo/_base/declare', 'dojo/_base/lang', 'dojo/on',
    'dojo/text!./templates/AllPoints.html', 'dojo/i18n!./nls/AllPoints',
    'spw/api/SpwBaseTemplatedWidget',
    'esri/geometry/Point', 'esri/SpatialReference',
    'esri/graphic', 'esri/symbols/SimpleMarkerSymbol',
    // il faut le charger car utilisé dans le template
    // mais nous ne sommes pas obligés de l'utiliser
    'dijit/form/ToggleButton'
], function(declare, lang, on,
            tmpl, labels, _Templated,
            Point, SpatialReference,
            Graphic, SimpleMarkerSymbol) {

    return declare(_Templated, {

        templateString: tmpl,

        labels: labels,

        // config de base pouvant être surchargée dans le widgets.json
        symbol: {
            color: [255, 255, 255, 64],
            size: 12,
            type: 'esriSMS',
            style: 'esriSMSCircle',
            outline: {
                color: [0, 0, 0, 255],
                width: 1,
                type: 'esriSLS',
                style: 'esriSLSSolid'
            }
        },

        // méthode appelée automatiquement après la création du widget
        postCreate: function() {
            this.inherited(arguments); // appelle la fonction de la classe parente

            // - this.own permet d'enregistrer l'event pour qu'il soit automatiquement supprimé
            //   à la destruction du widget.
            // - lang.hitch permet de créer une fonction avec un contexte particulier. Ainsi,
            //   le this de la fonction correspondera bien au widget
            this.own(on(this._allPointsButton, 'change', lang.hitch(this, this.onClic)));
        },

        onDeactivate: function() {
            this.inherited(arguments);

            this._allPointsButton.set('checked', false);
            this.removeGraphic();
        },

        onClic: function() {
            var checked = this._allPointsButton.get('checked');

            if (checked) {

                this.visibleGraphs = [];

                var visibleGraphs = this.visibleGraphs;

                var myMap = this.spwViewer.get('spwMap')

                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "http://localhost:9100/api/pointsCarto", false);
                xmlhttp.setRequestHeader("Content-Type", "application/json");
                xmlhttp.addEventListener("load", function(){

                                                 var objJSON = JSON.parse(this.responseText);

                                                 objJSON.forEach(function(entry) {
                                                     coord = entry.geometry.substring(entry.geometry.indexOf("(")+1,entry.geometry.indexOf(")")).split(" ");
                                                     y = coord[0];
                                                     x = coord[1];
                                                     srid = coord[2];

                                                     // construisons le point sur base des coordonnées et de la référence spatiale
                                                     var pt = new Point(x, y, new SpatialReference(srid));

                                                     var symbol = new SimpleMarkerSymbol(this.symbol);

                                                     // créons maintenant le graphique qui sera affiché sur la carte
                                                     // avec une certaine symbologie

                                                     graph = new Graphic(pt, symbol);

                                                     // et on affiche le graphique sur la carte
                                                     myMap.showFeature(graph);

                                                     visibleGraphs.push(graph);


                                                 })}
                                             );
                xmlhttp.send();

            }else {
                this.removeGraphic();
            }
        },

        removeGraphic: function() {

            var myMap = this.spwViewer.get('spwMap');

            this.visibleGraphs.forEach(function(entry) {
                myMap.removeFeature(entry);
            });

            this.visibleGraphs = [];
        },

    });

});