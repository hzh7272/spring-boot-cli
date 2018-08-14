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
                    vue.syncBreadcrumb(tag, nameArray);
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
                vue.syncBreadcrumb(tag, nameArray);
                vue.control.navTagList.push(tag);
            }

            window.sessionStorage.menuTagList = JSON.stringify(vue.control.navTagList);
        },
        // 同步面包屑
        syncBreadcrumb: function(tag, nameArray) {
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
        },
        closeTag: function(e, url) {
            // 删除tag
            for (var i in vue.control.navTagList) {
                var tag = vue.control.navTagList[i];
                if (url == tag.url) {
                    if ('primary' == tag.color) {
                        // 当前选中状态，跳转前一个标签页面
                        var lastIndex = parseInt(i) - 1;
                        vue.control.navTagList[lastIndex].color = 'primary';
                        vue.syncFrameUrl(vue.control.navTagList[lastIndex].url);
                    }
                    vue.control.navTagList.splice(i, 1);
                    window.sessionStorage.menuTagList = JSON.stringify(vue.control.navTagList);
                    break;
                }
            }
            // 删除frame
            for (var i in vue.control.frameList) {
                var frame = vue.control.frameList[i];
                if (url == frame.url) {
                    vue.control.frameList.splice(i, 1);
                    break;
                }
            }
        },
        closeAllTag: function () {
            vue.control.navTagList.splice(1, vue.control.navTagList.length);
            vue.control.navTagList[0].color = "primary";
            window.sessionStorage.menuTagList = JSON.stringify(vue.control.navTagList);

            // window.location.href = "#main";
            // index.currentUrl = "";
            // index.main = "view/main";
        },
        init: function () {
            var url = window.location.hash.replace('#', '');

            var name = '';

            for (var i in vue.control.navTagList) {
                var tag = vue.control.navTagList[i];
                if (url == tag.url) {
                    name = tag.name + '|' + tag.icon + '|' + tag.url;
                    vue.control.openName = [tag.parentName];
                    vue.control.currentUrl = name;
                }
            }

            if ('' != name) {
                var nameArray = name.split('|');
                vue.syncNavTag(nameArray);
                vue.syncFrameUrl(nameArray[2]);
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

vue.init();