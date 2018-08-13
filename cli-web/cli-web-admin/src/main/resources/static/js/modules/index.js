var vue = new Vue({
    el: '#cli',
    data: {
        control: {
            isFullScreen: false,
            openSubMenuName: [],
            breadcrumbList: [],
            navTagList: [],
            frameList: [],
            openName: [],
            currentUrl: ''
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

            vue.syncNavTag(nameArray);
            vue.syncFrameUrl(nameArray[2]);

            vue.control.currentUrl = name;
            window.location.href = '#' + nameArray[2];
        },
        // 子菜单打开事件
        getSubmenu: function(subMenu) {
            vue.control.openSubMenuName = subMenu;
        },
        // 同步URL标签
        syncNavTag: function(nameArray) {
            var name = nameArray[0];
            var icon = nameArray[1];
            var url = nameArray[2];
            var isExits = false;

            for (var i in vue.control.navTagList) {
                var tag = vue.control.navTagList[i];
                if (name == tag.name && url == tag.url) {
                    isExits = true;
                    tag.color = 'primary';
                    vue.control.openName = [tag.parentName];

                    vue.$nextTick(function() {
                        vue.$refs.myMenu.updateOpened();
                    });

                    var subMenuArray = vue.control.openName[0].split('|');

                    vue.control.breadcrumbList = [];
                    vue.control.breadcrumbList.push({
                        name: subMenuArray[0],
                        icon: subMenuArray[1]
                    });
                    vue.control.breadcrumbList.push({
                        name: nameArray[0],
                        icon: nameArray[1]
                    });
                } else {
                    tag.color = '';
                }
            }

            if (!isExits) {
                var tag = {
                    name: name,
                    url: url,
                    icon: icon,
                    color: "primary",
                    closable: true,
                    parentName: vue.control.openSubMenuName[0]
                };

                vue.control.openName = [tag.parentName];

                vue.$nextTick(function() {
                    vue.$refs.myMenu.updateOpened();
                });

                var subMenuArray = vue.control.openName[0].split('|');

                vue.control.breadcrumbList = [];
                vue.control.breadcrumbList.push({
                    name: subMenuArray[0],
                    icon: subMenuArray[1]
                });
                vue.control.breadcrumbList.push({
                    name: nameArray[0],
                    icon: nameArray[1]
                });

                vue.control.navTagList.push(tag);
            }

            window.sessionStorage.menuTagList = JSON.stringify(vue.control.navTagList);
        },
        // 更新iframe
        syncFrameUrl: function (url) {
            var isExits = false;
            var showIndex = 0;

            for (var i in vue.control.frameList) {
                var frame = vue.control.frameList[i];
                if (url == frame.url) {
                    isExits = true;
                    showIndex = i;
                } else {
                    frame.show = false;
                }
            }

            if (isExits) {
                vue.control.frameList[showIndex].show = true;
            } else {
                vue.control.frameList.push({
                    url: url,
                    show: true
                });
            }
        }
    },
    mounted: function () {
        var home = {
            name: '首页',
            url: "main",
            icon: 'icon-shouye',
            color: "primary",
            closable: false
        };

        if (undefined == window.sessionStorage.menuTagList) {
            if (0 == this.control.navTagList.length) {
                this.control.navTagList.push(home);
            }
        } else {
            this.control.navTagList = JSON.parse(window.sessionStorage.menuTagList);
            if (0 == this.control.navTagList.length) {
                this.control.navTagList.push(home);
            }
        }
    }
});