Repository to demonstrate Dokka [issue #1825](https://github.com/Kotlin/dokka/issues/1825).

Project is configured to use external Dokka documentation published from [KoAP].

When generating individual module documentation, references are properly linked to the [KoAP documentation].
However, when [documenting as a Gradle multi-module project], the references are no longer links.


[KoAP]: https://github.com/JuulLabs/koap
[KoAP documentation]: https://juullabs.github.io/koap/docs/index.html
[documenting as a Gradle multi-module project]: https://github.com/Kotlin/dokka#multi-module-projects
