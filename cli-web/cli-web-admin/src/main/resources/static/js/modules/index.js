var vue = new Vue({
    el: '#cli',
    data: {
        control: {
            isFullScreen: false,
            openSubMenuName: [],
            breadcrumbList: [],
            navTagList: []
        }
    },
    methods: {
        // 菜单标签栏左右控制
        tagScrollLeft: function() {
            document.getElementById('tag-box-list').scrollLeft = document.getElementById('tag-box-list').scrollLeft - 50;
        },
        tagScrollRight: function() {
            document.getElementById('tag-box-list').scrollLeft = document.getElementById('tag-box-list').scrollLeft + 50;
        },
        // 全屏
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
        // 退出全屏
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
        },
        // 跳转链接
        goHref: function (name) {
            var nameArray = name.split('|');
            var subMenuArray = vue.control.openSubMenuName[0].split('|');

            vue.control.breadcrumbList = [];
            vue.control.breadcrumbList.push({
                name: subMenuArray[0],
                icon: subMenuArray[1]
            });
            vue.control.breadcrumbList.push({
                name: nameArray[0],
                icon: nameArray[1]
            });

            vue.control.navTagList.push({
                name: nameArray[0],
                icon: nameArray[1]
            });
        },
        // 子菜单打开事件
        getSubmenu: function(subMenu) {
            vue.control.openSubMenuName = subMenu;
        }
    }
});