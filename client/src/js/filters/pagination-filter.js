angular.module('iss.hospital').filter('pagination', function() {
    return function(items, page, pageSize) {
        if (items === undefined || items === null) {
            return [];
        }
        if (pageSize === undefined || pageSize === null || pageSize < 0 || pageSize >= items.length) {
            return items;
        }

        var filtered = [];
        page = page - 1;
        filtered = items.slice(page * pageSize, parseInt((page * pageSize) + pageSize));
        return filtered;
    }
});
