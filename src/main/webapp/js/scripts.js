(function ($) {
    "use strict";

    $(window).on('load', function () {

        // Preloader
        $('.loader').fadeOut();
        $('.loader-mask').delay(350).fadeOut('slow');

        $(window).trigger("resize");
        initCounters();

    });


    // Init
    initOwlCarousel();
    initTextrotator();
    initMasonry();


    // Resize
    $(window).resize(function () {

        containerFullHeight();
        initMasonry();

    });


    /* Detect Browser Size
    -------------------------------------------------------*/
    var minWidth;
    if (Modernizr.mq('(min-width: 0px)')) {
        // Browsers that support media queries
        minWidth = function (width) {
            return Modernizr.mq('(min-width: ' + width + 'px)');
        };
    } else {
        // Fallback for browsers that does not support media queries
        minWidth = function (width) {
            return $(window).width() >= width;
        };
    }

    /* Mobile Detect
    -------------------------------------------------------*/
    if (/Android|iPhone|iPad|iPod|BlackBerry|Windows Phone/i.test(navigator.userAgent || navigator.vendor || window.opera)) {
        $("html").addClass("mobile");
        $('.dropdown-toggle').attr('data-toggle', 'dropdown');
    } else {
        $("html").removeClass("mobile");
    }

    /* IE Detect
    -------------------------------------------------------*/
    if (Function('/*@cc_on return document.documentMode===10@*/')()) {
        $("html").addClass("ie");
    }


    /* Sticky Navigation
    -------------------------------------------------------*/
    $(window).scroll(function () {

        scrollToTop();
        var $stickyNav = $('.nav--sticky');

        if ($(window).scrollTop() > 2 & minWidth(992)) {
            $stickyNav.addClass('sticky');
            $('.nav').addClass('sticky');
        } else {
            $stickyNav.removeClass('sticky');
            $('.nav').removeClass('sticky');
        }

    });

    function stickyNavRemove() {
        if (!minWidth(992)) {
            $('.nav--sticky').removeClass('sticky');
        }
    }


    // Onepage Nav
    $('#onepage-nav').on('click', 'li > a', function () {
        $("#navbar-collapse").collapse('hide');
    });


    // Smooth Scroll Navigation
    $('.local-scroll').localScroll({offset: {top: -60}, duration: 1500, easing: 'easeInOutExpo'});
    $('.local-scroll-no-offset').localScroll({offset: {top: 0}, duration: 1500, easing: 'easeInOutExpo'});


    /* Mobile Navigation
    -------------------------------------------------------*/
    var $dropdownTrigger = $('.nav__dropdown-trigger');
    $dropdownTrigger.on('click', function () {
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
        } else {
            $(this).addClass("active");
        }
    });


    if ($('html').hasClass('mobile')) {
        $('body').on('click', function () {
            $('.nav__dropdown-menu').addClass('hide-dropdown');
        });

        $('.nav__dropdown').on('click', '> a', function (e) {
            e.preventDefault();
        });

        $('.nav__dropdown').on('click', function (e) {
            e.stopPropagation();
            $('.nav__dropdown-menu').removeClass('hide-dropdown');
        });
    }


    /* Search
    -------------------------------------------------------*/
    var $navSearchWrap = $('.nav__search-wrap');
    var $navSearchTrigger = $('#nav__search-trigger');
    var $navSearchClose = $('#nav__search-close');

    $navSearchTrigger.on('click', function (e) {
        e.preventDefault();
        $navSearchWrap.animate({opacity: 'toggle'}, 500);
        $navSearchTrigger.add($navSearchClose).addClass("open");
        $('.nav__search-input').focus();
    });

    $navSearchClose.on('click', function (e) {
        e.preventDefault();
        $navSearchWrap.animate({opacity: 'toggle'}, 500);
        $navSearchTrigger.add($navSearchClose).removeClass("open");
    });

    function closeSearch() {
        $navSearchWrap.fadeOut(200);
        $navSearchTrigger.add($navSearchClose).removeClass("open");
    }

    $(document.body).on('click', function (e) {
        closeSearch();
    });

    $("#nav__search-trigger, .nav__search-input").on('click', function (e) {
        e.stopPropagation();
    });


    /* Text Rotator
    -------------------------------------------------------*/
    function initTextrotator() {

        $(".rotate").textrotator({
            animation: "dissolve", // You can pick the way it animates when rotating through words. Options are dissolve (default), fade, flip, flipUp, flipCube, flipCubeUp and spin.
            separator: ",",
            speed: 3000
        });
    }

    /* Counters
    -------------------------------------------------------*/
    function initCounters() {
        $('.counter').appear(function () {
            $('.counter__timer').countTo({
                speed: 4000,
                refreshInterval: 60,
                formatter: function (value, options) {
                    return value.toFixed(options.decimals);
                }
            });
        });
    }


    /* Progress Bars
    -------------------------------------------------------*/
    $('#animated-skills').appear(function () {

        function loadDaBars() {
            var bar = $('.progress__bar');
            var bar_width = $(this);
            $(function () {
                $(bar).each(function () {
                    bar_width = $(this).attr('aria-valuenow');
                    $(this).width(bar_width + '%');
                });
            });
        }

        loadDaBars();
    });


    /* Accordion
    -------------------------------------------------------*/
    function toggleChevron(e) {
        $(e.target)
            .prev('.accordion-panel__heading')
            .find("a")
            .toggleClass('plus minus');
    }

    $('#accordion').on('hide.bs.collapse', toggleChevron);
    $('#accordion').on('show.bs.collapse', toggleChevron);


    /* Tabs
    -------------------------------------------------------*/
    $('.tabs__link').on('click', function (e) {
        var currentAttrValue = $(this).attr('href');
        $('.tabs__content ' + currentAttrValue).stop().fadeIn(1000).siblings().hide();
        $(this).parent('li').addClass('active').siblings().removeClass('active');
        e.preventDefault();
    });


    /* Lightbox popup
    -------------------------------------------------------*/

    $('.lightbox-img, .lightbox-video').magnificPopup({
        callbacks: {
            elementParse: function (item) {
                if (item.el.context.className == 'lightbox-video') {
                    item.type = 'iframe';
                } else {
                    item.type = 'image';
                }
            }
        },
        type: 'image',
        closeBtnInside: false,
        gallery: {
            enabled: true
        },
        image: {
            titleSrc: 'title',
            verticalFit: true
        }
    });

    // Single video lightbox
    $('.single-video-lightbox').magnificPopup({
        type: 'iframe',
        closeBtnInside: false,
        tLoading: 'Loading image #%curr%...'
    });


    /* Flexslider / Blog Masonry
    -------------------------------------------------------*/

    $('#flexslider-single').flexslider({
        animation: "slide",
        directionNav: true,
        touch: true,
        slideshow: false,
        prevText: ["<i class='fa fa-angle-left'></i>"],
        nextText: ["<i class='fa fa-angle-right'></i>"],
        start: function () {
            var $container = $('.masonry');
            $container.imagesLoaded(function () {
                $container.isotope({
                    itemSelector: '.masonry-item',
                    layoutMode: 'masonry'
                });
            });
        }
    });


    /* Full Height Container
    -------------------------------------------------------*/

    function containerFullHeight() {
        (function ($) {
            $(".container-full-height").height($(window).height());
        })(jQuery);
    }


    /* Wow Animations
    -------------------------------------------------------*/

    var wow = new WOW({
        offset: 50,
        mobile: false
    });

    wow.init();


    /* Scroll to Top
    -------------------------------------------------------*/
    function scrollToTop() {
        var scroll = $(window).scrollTop();
        var $backToTop = $("#back-to-top");
        if (scroll >= 50) {
            $backToTop.addClass("show");
        } else {
            $backToTop.removeClass("show");
        }
    }

    $('a[href="#top"]').on('click', function () {
        $('html, body').animate({scrollTop: 0}, 1350, "easeInOutQuint");
        return false;
    });

})(jQuery);
