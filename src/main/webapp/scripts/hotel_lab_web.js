/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$(document).ready(function (){
(function ($, window, document) {
    $(function () {

        var activeUrl = window.location.href;
        var activeId = activeUrl.substring(activeUrl.lastIndexOf('id='), (activeUrl.lastIndexOf('id=') + 4));
        activeId = activeId.substring(3, 4);
        if (activeId === activeUrl) {
            activeId = 0;
        }
        ;

        var $activeItem = $('#' + activeId);
        var $selectable = $('.selectable');
        var $insertBtn = $('#insertBtn');
        var $updateBtn = $('#updateBtn');
        var $deleteBtn = $('#deleteBtn');
        var $clearBtn = $('#clearBtn');
        var $hotelForm = $('#hotelForm');
        var $formName = $('#name');
        var $formAddress = $('#address');
        var $formCity = $('#city');
        var $formState = $('#state');
        var $formPostal = $('#postal');
        var $formNotes = $('#notes');
        var $error = $('#error');
        var $hotelIdHeader = $('#hotelIdHeader');
        var $hotelNameHeader = $('#hotelNameHeader');
        var $homeBtn = $('#homeBtn');
        var $searchBtn = $('#searchBtn');
        var $searchForm = $('#searchForm');
        var $dropdown = $('#dropdown');
        
        var nameSortUp = true;
        var idSortUp = true;

        $activeItem.addClass("selected");
        
        $dropdown.on('change', function(){
            var option = $dropdown.val(); 
//            $('#test').text(option);
            if(option === "all"){
                $('#searchByGroup').addClass('hidden');
            } else {
                $('#searchByGroup').removeClass('hidden');
            }
        });
        
        $searchBtn.on('click', function(){
            if($('#searchByGroup').hasClass('hidden')){
                $searchForm.attr('action', "/HotelLabMavenJPA/hotelweb?dropdownOption=" + $('#dropdown').val());
                $searchForm.submit();
            } else if ($('#searchVal').val() === ""){
                $('#test').text("error");
            } else {
                $searchForm.attr('action', "/HotelLabMavenJPA/hotelweb?dropdownOption=" + $('#dropdown').val());
                $searchForm.submit();
            }
            
        });
        
        $homeBtn.on('click', function(){
            $hotelForm.attr('action', "/HotelLabMavenJPA/hotelweb");
            $hotelForm.submit();
        });
        
        $selectable.on('click', function () {
            var id = $(this).attr('id');
            $hotelForm.attr('action', "/HotelLabMavenJPA/hotelweb?type=view&hotel_id=" + id + "&arraySpace=" + $(this).attr("arraySpace"));
            $hotelForm.submit();
        });

        $selectable.on('mouseover', function () {
            $(this).css("background-color", "#78B220");
        });

        $selectable.on('mouseout', function () {
            if ($(this).hasClass("selected")) {

            } else {
                $(this).css("background-color", "#ADFF2F");
            }
        });

        $insertBtn.on('click', function () {

            $hotelForm.attr('action', "/HotelLabMavenJPA/hotelweb?type=create&hotel_id=0");

            if ($formName.val().length > 0 && $formAddress.val().length > 0 && $formCity.val().length > 0 &&
                    $formState.val().length > 0 && $formPostal.val().length > 0) {
                $hotelForm.submit();
            } else {
                $error.removeClass('hidden');
                $error.text("All fields are required except Notes.");
            }

        });

//    $('#viewBtn').click(function () {
//        $('#hotelForm').attr('action', "/HotelLabWeb/hotelweb?type=normal&hotel_id=0");
//        $('#hotelForm').submit();
//    });

        $updateBtn.on('click', function () {
            var url = window.location.href;
            var type = url.substring(url.indexOf('=') + 1, url.indexOf('&'));
            var id = url.substring(url.lastIndexOf('id='), (url.lastIndexOf('id=') + 4));
            id = id.substring(3, 4);
            if (type === "view" && !$hotelForm.hasClass('cleared')) {
                $hotelForm.attr('action', "/HotelLabMavenJPA/hotelweb?type=update&hotel_id=" + id + "&arraySpace=" + $('#' + id).attr("arraySpace"));
                $hotelForm.submit();
            } else {
                $error.removeClass('hidden');
                $error.text("Please select a hotel to update.");
            }
        });

        $deleteBtn.on('click', function () {
            var url = window.location.href;
            var type = url.substring(url.indexOf('=') + 1, url.indexOf('&'));
            var id = url.substring(url.lastIndexOf('id='), (url.lastIndexOf('id=') + 4));
            id = id.substring(3, 4);
            if ((type === "view" || type === "update") && !$('#hotelForm').hasClass('cleared')) {
                $hotelForm.attr('action', "/HotelLabMavenJPA/hotelweb?type=delete&hotel_id=" + id + "&arraySpace=" + $('#' + id).attr("arraySpace"));
                $hotelForm.submit();
            } else {
                $error.removeClass('hidden');
                $error.text("Please select a hotel to delete.");
            }
        });

        $clearBtn.on('click', function () {
            $hotelForm.addClass('cleared');
            $('.selected').removeClass('selected');
            $selectable.css('background-color', '#ADFF2F');
            $formName.val('');
            $formAddress.val('');
            $formCity.val('');
            $formState.val('');
            $formPostal.val('');
            $formNotes.val('');
        });

        $hotelIdHeader.on('click', function () {
            if (idSortUp) {
//               console.log('Up sort ID');
                $(this).html("Hotel Id<span class='glyphicon glyphicon-chevron-up'></span>");
            } else {
//               console.log('Down sort ID');
            }
            idSortUp = !idSortUp;
        });

        $hotelNameHeader.on('click', function () {
            if (nameSortUp) {
//               console.log('Up sort Name');
            } else {
//               console.log('Down sort Name');     
            }
            nameSortUp = !nameSortUp;
        });

    });
}(window.jQuery, window, document));
