var vue = new Vue({
    el: '#cli',
    data: {
        control: {
            isFullScreen: false
        }
    },
    methods: {
        tagScrollLeft: function() {
            document.getElementById('tag-box-list').scrollLeft = document.getElementById('tag-box-list').scrollLeft - 50;
            cliComm.notice.success(vue, "sdfs", "sdfasdfasdfasdfasdf");
        },
        tagScrollRight: function() {
            document.getElementById('tag-box-list').scrollLeft = document.getElementById('tag-box-list').scrollLeft + 50;
        },
        fullScreen: function () {
            var docElm = document.documentElement;
            if (docElm.requestFullscreen) { //W3C
                docElm.requestFullscreen();
            } else if (docElm.mozRequestFullScreen) { //FireFox
                docElm.mozRequestFullScreen();
            } else if (docElm.webkitRequestFullScreen) { //Chrome等
                docElm.webkitRequestFullScreen();
            } else if (docElm.msRequestFullscreen) { //IE 11
                docElm.msRequestFullscreen();
            }
            vue.control.isFullScreen = true;
        },
        exitFullScreen: function () {
            if (document.exitFullscreen) { //W3C
                document.exitFullscreen();
            } else if (document.mozCancelFullScreen) { //FireFox
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) { //Chrome等
                document.webkitCancelFullScreen();
            } else if (document.msExitFullscreen) { //IE 11
                document.msExitFullscreen();
            }
            vue.control.isFullScreen = false;
        }
    }
});

$('#tag-box-list').bind('mousewheel', function(event, delta, deltaX, deltaY) {
    if (window.console && console.log) {
        var scrollLeft = this.scrollLeft;
        if (-1 == deltaY) {
            this.scrollLeft = scrollLeft + 50;
        } else {
            this.scrollLeft = scrollLeft - 50;
        }
    }
});