(ns clj-momo.lib.clj-time.core
  (:refer-clojure :exclude [= extend second])
  #?(:cljs (:require-macros [clj-momo.lib.clj-time.macros :refer [immigrate]]))
  (:require [clj-momo.lib.clj-time.coerce :as time-coerce]
            #?(:clj  [clj-momo.lib.clj-time.macros :refer [immigrate]])
            #?(:clj  [clj-time.core :as time-delegate]
               :cljs [cljs-time.core :as time-delegate]))
  #?(:clj
     (import [java.util Calendar Date GregorianCalendar TimeZone]
             [org.joda.time ReadablePartial ReadableInstant ReadablePeriod
              DateTime DateTimeConstants DateTimeZone Period])
     :cljs
     (:import goog.date.Date
              goog.date.DateTime
              goog.date.UtcDateTime)))

(def =
  #?(:clj  clojure.core/=
     :cljs time-delegate/=))

(immigrate time-delegate
           [abuts?
            days
            days?
            end
            extend
            hours
            hours?
            interval
            local-date
            local-date-time
            millis
            mins-ago
            minutes
            minutes?
            months
            months?
            now
            number-of-days-in-the-month
            overlap
            overlaps?
            seconds
            seconds?
            start
            time-now
            today
            today-at
            utc
            weeks
            weeks?
            years
            years?])

(defprotocol DateTimeProtocol
  (year [this])
  (month [this])
  (day [this])
  (day-of-week [this])
  (hour [this])
  (minute [this])
  (second [this])
  (milli [this])
  (equal? [this that])
  (after? [this that])
  (before? [this that])
  (plus- #?(:clj  [this ^ReadablePeriod period]
            :cljs [this period]))
  (minus- #?(:clj  [this ^ReadablePeriod period]
             :cljs [this period]))
  (first-day-of-the-month- [this])
  (last-day-of-the-month- [this])
  (week-number-of-year [this]))

#?(:clj
   (def ^:private TimeZoneUTC
     (TimeZone/getTimeZone "UTC")))

#?(:clj
   (extend-protocol DateTimeProtocol
     org.joda.time.DateTime
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this ^ReadableInstant that]
       (time-delegate/equal? this that))
     (after? [this ^ReadableInstant that]
       (time-delegate/after? this that))
     (before? [this ^ReadableInstant that]
       (time-delegate/before? this that))
     (plus- [this ^ReadableInstant period]
       (time-delegate/plus- this period))
     (minus- [this ^ReadableInstant period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))

     org.joda.time.LocalDateTime
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this ^ReadableInstant that]
       (time-delegate/equal? this that))
     (after? [this ^ReadableInstant that]
       (time-delegate/after? this that))
     (before? [this ^ReadableInstant that]
       (time-delegate/before? this that))
     (plus- [this ^ReadableInstant period]
       (time-delegate/plus- this period))
     (minus- [this ^ReadableInstant period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))

     org.joda.time.LocalDate
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this ^ReadableInstant that]
       (time-delegate/equal? this that))
     (after? [this ^ReadableInstant that]
       (time-delegate/after? this that))
     (before? [this ^ReadableInstant that]
       (time-delegate/before? this that))
     (plus- [this ^ReadableInstant period]
       (time-delegate/plus- this period))
     (minus- [this ^ReadableInstant period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))

     org.joda.time.YearMonth
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (equal? [this ^ReadablePartial that]
       (time-delegate/equal? this that))
     (after? [this ^ReadablePartial that]
       (time-delegate/after? this that))
     (before? [this ^ReadablePartial that]
       (time-delegate/before? this that))
     (plus- [this ^ReadablePeriod period]
       (time-delegate/plus- this period))
     (minus- [this ^ReadablePeriod period]
       (time-delegate/minus- this period))

     org.joda.time.LocalTime
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this ^ReadablePartial that]
       (time-delegate/equal? this that))
     (after? [this ^ReadablePartial that]
       (time-delegate/after? this that))
     (before? [this ^ReadablePartial that]
       (time-delegate/before? this that))
     (plus- [this ^ReadablePeriod period]
       (time-delegate/plus- this period))
     (minus- [this ^ReadablePeriod period]
       (time-delegate/minus- this period))

     java.util.Date
     (year [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/YEAR))
     (month [this]
       (inc
        (.get (doto (GregorianCalendar.)
                (.setTimeZone TimeZoneUTC)
                (.setTime this))
              Calendar/MONTH)))
     (day [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/DAY_OF_MONTH))
     (day-of-week [this]
       (condp = (.get (doto (GregorianCalendar.)
                        (.setTimeZone TimeZoneUTC)
                        (.setTime this))
                      Calendar/DAY_OF_WEEK_IN_MONTH)
         Calendar/MONDAY DateTimeConstants/MONDAY
         Calendar/TUESDAY DateTimeConstants/TUESDAY
         Calendar/WEDNESDAY DateTimeConstants/WEDNESDAY
         Calendar/THURSDAY DateTimeConstants/THURSDAY
         Calendar/FRIDAY DateTimeConstants/FRIDAY
         Calendar/SATURDAY DateTimeConstants/SATURDAY
         Calendar/SUNDAY DateTimeConstants/SUNDAY))
     (hour [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/HOUR_OF_DAY))
     (minute [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/MINUTE))
     (second [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/SECOND))
     (milli [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/MILLISECOND))
     (equal? [this that]
       (.equals this that))
     (after? [this that]
       (< 0 (.compareTo this that)))
     (before? [this that]
       (> 0 (.compareTo this that)))
     (plus- [this ^ReadableInstant period]
       (Date.
        (.getMillis
         (plus-
          (time-coerce/to-date-time this)
          period))))
     (minus- [this ^ReadablePeriod period]
       (Date.
        (.getMillis
         (minus-
          (time-coerce/to-date-time this)
          period))))
     (first-day-of-the-month- [this]
       (Date.
        (.getMillis
         (first-day-of-the-month-
          (time-coerce/to-date-time this)))))
     (last-day-of-the-month- [this]
       (Date.
        (.getMillis
         (last-day-of-the-month-
          (time-coerce/to-date-time this)))))
     (week-number-of-year [this]
       (.get (doto (GregorianCalendar.)
               (.setTimeZone TimeZoneUTC)
               (.setTime this))
             Calendar/WEEK_OF_YEAR)))

   :cljs
   (extend-protocol DateTimeProtocol
     js/Date
     (year [this]
       (year
        (.fromTimestamp DateTime (.getTime this))))
     (month [this]
       (month
        (.fromTimestamp DateTime (.getTime this))))
     (day [this]
       (day
        (.fromTimestamp DateTime (.getTime this))))
     (day-of-week [this]
       (day-of-week
        (.fromTimestamp DateTime (.getTime this))))
     (hour [this]
       (hour
        (.fromTimestamp DateTime (.getTime this))))
     (minute [this]
       (minute
        (.fromTimestamp DateTime (.getTime this))))
     (second [this]
       (second
        (.fromTimestamp DateTime (.getTime this))))
     (milli [this]
       (milli
        (.fromTimestamp DateTime (.getTime this))))
     (equal? [this that]
       (equal?
        (.fromTimestamp DateTime (.getTime this))
        (.fromTimestamp DateTime (.getTime that))))
     (after? [this that]
       (after?
        (.fromTimestamp DateTime (.getTime this))
        (.fromTimestamp DateTime (.getTime that))))
     (before? [this that]
       (before?
        (.fromTimestamp DateTime (.getTime this))
        (.fromTimestamp DateTime (.getTime that))))
     (plus- [this period]
       (plus-
        (.fromTimestamp DateTime (.getTime this))
        period))
     (minus- [this period]
       (minus-
        (.fromTimestamp DateTime (.getTime this))
        period))
     (first-day-of-the-month- [this]
       (first-day-of-the-month-
        (.fromTimestamp DateTime (.getTime this))))
     (last-day-of-the-month- [this]
       (last-day-of-the-month-
        (.fromTimestamp DateTime (.getTime this))))
     (week-number-of-year [this]
       (week-number-of-year
        (.gromTimestamp DateTime (.getTime this))))

     goog.date.UtcDateTime
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this that]
       (when that
         (time-delegate/equal? this that)))
     (after? [this that]
       (time-delegate/after? this that))
     (before? [this that]
       (time-delegate/before? this that))
     (plus- [this period]
       (time-delegate/plus- this period))
     (minus- [this period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))

     goog.date.DateTime
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this that]
       (when that
         (time-delegate/equal? this that)))
     (after? [this that]
       (time-delegate/after? this that))
     (before? [this that]
       (time-delegate/before? this that))
     (plus- [this period]
       (time-delegate/plus- this period))
     (minus- [this period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))

     goog.date.Date
     (year [this] (time-delegate/year this))
     (month [this] (time-delegate/month this))
     (day [this] (time-delegate/day this))
     (day-of-week [this] (time-delegate/day-of-week this))
     (hour [this] (time-delegate/hour this))
     (minute [this] (time-delegate/minute this))
     (second [this] (time-delegate/second this))
     (milli [this] (time-delegate/milli this))
     (equal? [this that]
       (when that
         (time-delegate/equal? this that)))
     (after? [this that]
       (time-delegate/after? this that))
     (before? [this that]
       (time-delegate/before? this that))
     (plus-[this period]
       (time-delegate/plus- this period))
     (minus- [this period]
       (time-delegate/minus- this period))
     (first-day-of-the-month- [this]
       (time-delegate/first-day-of-the-month- this))
     (last-day-of-the-month- [this]
       (time-delegate/last-day-of-the-month- this))
     (week-number-of-year [this]
       (time-delegate/week-number-of-year this))))

(defn internal-now []
  #?(:clj (Date.)
     :cljs (now)))

(defn date-time
  ([year]
   (time-delegate/date-time year))
  ([year month]
   (time-delegate/date-time year month))
  ([year month day]
   (time-delegate/date-time year month day))
  ([year month day hour]
   (time-delegate/date-time year month day hour))
  ([year month day hour minute]
   (time-delegate/date-time year month day hour minute))
  ([year month day hour minute second]
   (time-delegate/date-time year month day hour minute second))
  ([year month day hour minute second millis]
   (time-delegate/date-time year month day hour minute second millis)))

#?(:clj
   (defn internal-date
     ([year]
      (.getTime (doto (GregorianCalendar. year 0 1)
                  (.setTimeZone TimeZoneUTC))))
     ([year month]
      (.getTime (doto (GregorianCalendar. year (dec month) 1)
                  (.setTimeZone TimeZoneUTC))))
     ([year month day]
      (.getTime (doto (GregorianCalendar. year (dec month) day)
                  (.setTimeZone TimeZoneUTC))))
     ([year month day hour]
      (.getTime (doto (GregorianCalendar. year (dec month) day hour 0)
                  (.setTimeZone TimeZoneUTC))))
     ([year month day hour minute]
      (.getTime (doto (GregorianCalendar. year (dec month) day hour minute)
                  (.setTimeZone TimeZoneUTC))))
     ([year month day hour minute second]
      (.getTime (doto (GregorianCalendar. year (dec month) day hour minute second)
                  (.setTimeZone TimeZoneUTC))))
     ([year month day hour minute second millis]
      (.getTime (doto (GregorianCalendar. year (dec month) day hour minute second)
                  (.setTimeZone TimeZoneUTC)
                  (.set Calendar/MILLISECOND millis)))))

   :cljs
   (def internal-date date-time))

(defn plus
  (#?(:clj  [dt ^ReadablePeriod p]
      :cljs [dt p])
   (plus- dt p))
  ([dt p & ps]
   (reduce plus- (plus- dt p) ps)))

(defn minus
  (#?(:clj  [dt ^ReadablePeriod p]
      :cljs [dt p])
   (minus- dt p))
  ([dt p & ps]
   (reduce minus- (minus- dt p) ps)))

(defn ago
  #?(:clj  [^Period period]
     :cljs [period])
  (minus (now) period))

(defn yesterday []
  (-> 1 days ago))

(defn from-now
  #?(:clj  [^Period period]
     :cljs [period])
  (plus (now) period))

(defn earliest
  ([dt1 dt2]
   (if (before? dt1 dt2) dt1 dt2))
  ([dts]
   (reduce earliest dts)))

(defn latest
  ([dt1 dt2]
   (if (after? dt1 dt2) dt1 dt2))
  ([dts]
   (reduce latest dts)))

(defn first-day-of-the-month
  (#?(:clj  [^long year ^long month]
      :cljs [year month])
   (first-day-of-the-month- (date-time year month)))
  ([dt]
   (first-day-of-the-month- dt)))

(defn last-day-of-the-month
  (#?(:clj  [^long year ^long month]
      :cljs [year month])
   (last-day-of-the-month- (date-time year month)))
  ([dt]
   (last-day-of-the-month- dt)))

(defprotocol InTimeUnitProtocol
  (in-millis [this] "Return the time in milliseconds.")
  (in-seconds [this] "Return the time in seconds.")
  (in-minutes [this] "Return the time in minutes.")
  (in-hours [this] "Return the time in hours.")
  (in-days [this] "Return the time in days.")
  (in-weeks [this] "Return the time in weeks")
  (in-months [this] "Return the time in months")
  (in-years [this] "Return the time in years"))

#?(:cljs
   (immigrate time-delegate
              [Interval
               Period
               period?
               period-type?]))

#?(:clj
   (extend-protocol InTimeUnitProtocol
     org.joda.time.Interval
     (in-millis [this]
       (time-delegate/in-millis this))
     (in-seconds [this]
       (time-delegate/in-seconds this))
     (in-minutes [this]
       (time-delegate/in-minutes this))
     (in-hours [this]
       (time-delegate/in-hours this))
     (in-days [this]
       (time-delegate/in-days this))
     (in-weeks [this]
       (time-delegate/in-weeks this))
     (in-months [this]
       (time-delegate/in-months this))
     (in-years [this]
       (time-delegate/in-years this))

     org.joda.time.ReadablePeriod
     (in-millis [this]
       (time-delegate/in-millis this))
     (in-seconds [this]
       (time-delegate/in-seconds this))
     (in-minutes [this]
       (time-delegate/in-minutes this))
     (in-hours [this]
       (time-delegate/in-hours this))
     (in-days [this]
       (time-delegate/in-days this))
     (in-weeks [this]
       (time-delegate/in-weeks this))
     (in-months [this]
       (time-delegate/in-months this))
     (in-years [this]
       (time-delegate/in-years this)))

   :cljs
   (extend-protocol InTimeUnitProtocol
     Period
     (in-millis [this]
       (time-delegate/in-millis this))
     (in-seconds [this]
       (time-delegate/in-seconds this))
     (in-minutes [this]
       (time-delegate/in-minutes this))
     (in-hours [this]
       (time-delegate/in-hours this))
     (in-days [this]
       (time-delegate/in-days this))
     (in-weeks [this]
       (time-delegate/in-weeks this))
     (in-months [this]
       (time-delegate/in-months this))
     (in-years [this]
       (time-delegate/in-years this))

     Interval
     (in-millis [this]
       (time-delegate/in-millis this))
     (in-seconds [this]
       (time-delegate/in-seconds this))
     (in-minutes [this]
       (time-delegate/in-minutes this))
     (in-hours [this]
       (time-delegate/in-hours this))
     (in-days [this]
       (time-delegate/in-days this))
     (in-weeks [this]
       (time-delegate/in-weeks this))
     (in-months [this]
       (time-delegate/in-months this))
     (in-years [this]
       (time-delegate/in-years this))))

#?(:clj
   (defn within?
     ([interval date]
      (time-delegate/within? interval
                             (time-coerce/to-date-time date)))
     ([start end date]
      (time-delegate/within? (time-coerce/to-date-time start)
                             (time-coerce/to-date-time end)
                             (time-coerce/to-date-time date))))

   :cljs
   (def within? time-delegate/within?))
