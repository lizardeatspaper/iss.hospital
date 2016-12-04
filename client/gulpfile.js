var gulp = require('gulp');
var concat = require('gulp-concat');
var flatten = require('gulp-flatten');
var gulpFilter = require('gulp-filter');
var inject = require('gulp-inject');
var less = require('gulp-less');
var jscs = require('gulp-jscs');
var jshint = require('gulp-jshint');
var livereload = require('gulp-livereload');
var order = require('gulp-order');
var rename = require('gulp-order');
var templateCache = require('gulp-angular-templatecache');
var sourceMaps = require('gulp-sourcemaps');
var uglify = require('gulp-uglify');
var del = require('del');
var mainBowerFiles = require('main-bower-files');
var runSequence = require('run-sequence');

require('gulp-watch');

/**
 * CONSTANTS
 */
var options = {

	paths: {
		src: {
			index: './src/index.html',
			dir: './src',
			mainLess: './src/less/iss-hospital.less',
			less: [
				'./src/less/**/*.less',
				'./src/less/*.less'
			],
			watchLess: [
				'./src/less/**/*.less'
			],
			js: [
				'./src/js/**/*.js'
			],
			views: [
				'./src/views/**/*.html'
			],
			images: [
				'./src/img/**/*'
			],
			fonts: [
				'./src/fonts/**/*.*'
			],
			lib: {
				less: [
					'./src/bower_components/bootstrap/less/bootstrap.less',
					'./src/bower_components/font-awesome/less/font-awesome.less'
				],
				fonts: [
					'./src/bower_components/bootstrap/dist/fonts/glyphicons-halflings-regular.*',
					'./src/bower_components/font-awesome/fonts/fontawesome-webfont.*'
				]
			}
		},
		dist: {
			dir: '../server/src/main/webapp/',
			clean: '../server/src/main/webapp/',
			lib: {
				js: '../server/src/main/webapp/lib/',
				css: '../server/src/main/webapp/lib/',
				fonts: '../server/src/main/webapp/fonts/',
				inject: [
					'../server/src/main/webapp/lib/**/*.*'
				]
			},
			css: '../server/src/main/webapp/css',
			js: '../server/src/main/webapp/js',
			templateCache: '../server/src/main/webapp/js',
			inject: [
				'../server/src/main/webapp/css/**/*.*',
				'../server/src/main/webapp/js/**/*.*'
			],
			images: '../server/src/main/webapp/img',
			fonts: '../server/src/main/webapp/fonts',
			index: '../server/src/main/webapp/'
		}
	},

	lint: [
		'./gulpfile.js',
		'./src/js/**/*.js'
	],

	concatNames: {
		lib: {
			css: 'lib.css',
			js: 'lib.js'
		},
		app: {
			js: 'iss-hospital.js',
			templates: 'templates.js'
		}
	},

	assetsOrder: {
		lib: [
			'**/jquery.js',
			'**/bootstrap.js',
			'**/angular.js'
		],
		app: [
			'**/templates.js',
			'**/app.js'
		]
	},

	bowerConfig: {
		paths: {
			bowerDirectory: './src/bower_components',
			bowerrc: './src/.bowerrc',
			bowerJson: './src/bower.json'
		}
	}
};

/**
 * Removes compiled css and js files.
 */
gulp.task('clean', function() {
	return del(options.paths.dist.clean, { force: true });
});

/**
 * Compiles less to css.
 */
gulp.task('compile-css', function() {
	return gulp.src(options.paths.src.mainLess)
		.pipe(less())
		.pipe(gulp.dest(options.paths.dist.css));
});

gulp.task('compile-lib-css', function() {
	return gulp.src(options.paths.src.lib.less)
		.pipe(less())
		.pipe(gulp.dest(options.paths.dist.lib.css));
});

gulp.task('lint', function() {
	return gulp.src(options.lint)
		.pipe(jshint())
		.pipe(jshint.reporter('default'))
		.pipe(jscs())
		.pipe(jscs.reporter());
});

/**
 * Copy library css files.
 */
gulp.task('copy-lib-css', function() {
	return gulp.src(mainBowerFiles(options.bowerConfig))
		.pipe(gulpFilter('**/*.css'))
		.pipe(gulp.dest(options.paths.dist.lib.css))
		.pipe(livereload());
});

/**
 * Copy lib fonts
 */
gulp.task('copy-lib-fonts', function() {
	return gulp.src(options.paths.src.lib.fonts)
		.pipe(flatten())
		.pipe(gulp.dest(options.paths.dist.lib.fonts))
		.pipe(livereload());
});

/**
 * Copy library js files.
 */
gulp.task('copy-lib-js', function() {
	return gulp.src(mainBowerFiles(options.bowerConfig))
		.pipe(gulpFilter('**/*.js'))
		.pipe(gulp.dest(options.paths.dist.lib.js))
		.pipe(livereload());
});

gulp.task('copy-js', function() {
	return gulp.src(options.paths.src.js)
		.pipe(gulp.dest(options.paths.dist.js));
});

gulp.task('copy-templates', function() {
	return gulp.src(options.paths.src.views)
		.pipe(templateCache({
			standalone: true
		}))
		.pipe(gulp.dest(options.paths.dist.templateCache));
});

gulp.task('copy-images', function() {
	return gulp.src(options.paths.src.images)
		.pipe(gulp.dest(options.paths.dist.images))
		.pipe(livereload());
});

gulp.task('copy-fonts', function() {
	return gulp.src(options.paths.src.fonts)
		.pipe(gulp.dest(options.paths.dist.fonts))
		.pipe(livereload());
});

gulp.task('inject', function() {
	return gulp.src(options.paths.src.index)
		.pipe(
			inject(
				gulp.src(options.paths.dist.lib.inject)
				.pipe(order(options.assetsOrder.lib)),
				{name: 'lib', ignorePath: '/dist/'}
			)
		)
		.pipe(
			inject(
				gulp.src(options.paths.dist.inject)
				.pipe(order(options.assetsOrder.app)),
				{ name: 'app', ignorePath: '/dist/'}
			)
		)
		.pipe(gulp.dest(options.paths.dist.index))
		.pipe(livereload());
});

gulp.task('copy-assets', [
	'copy-lib-js',
	'copy-lib-css',
	'copy-lib-fonts',
	'copy-js',
	'copy-templates',
	'copy-images',
	'copy-fonts'
]);

gulp.task('build-app', [
	'compile-css',
	'compile-lib-css'
], function(callback) {
	runSequence(
		'copy-assets',
		'inject',
		callback
	);
});

gulp.task('build', function(callback) {
	runSequence(
		'clean',
		['build-app'],
		callback
	);
});

gulp.task('watch-app', function() {
	gulp.watch([].concat(
		['gulpfile.js'],
		options.paths.src.watchLess,
		options.lint,
		options.paths.src.views
	),  ['build-app']);
});

gulp.task('livereload-listen', function() {
	livereload.listen({quite:true});
});

gulp.task('watch', ['livereload-listen', 'watch-app']);

gulp.task('build+watch', function(callback) {
	runSequence(
		'build',
		'watch',
		callback
	);
});

gulp.task('default', ['build+watch']);
